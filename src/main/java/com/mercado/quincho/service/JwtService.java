package com.mercado.quincho.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Servicio para generar tokens JWT utilizados en la autenticación y 
 * autorización de usuarios.
 * Este servicio proporciona métodos para crear tokens JWT con información del
 * usuario y reclamaciones adicionales.
 * 
 * @author QuinSDev
 */
@Service
public class JwtService {
    
    private static final String
            SECRET_KEY = "GJAL3AUFN2KVJNEKNE3JY5SGSJHT51SJTSBTR5GBSRHSKY3GS1SG";
    
    /**
     * Genera un token JWT para un usuario.
     * 
     * @param user: El UserDetails (detalles del usuario) para el cual se genera
     * el token.
     * @return El token generado.
     */
    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);
    }
    
    /**
     * Genera un token JWT con reclamaciones adicionales para un usuario.
     * 
     * @param extraClaims: Las reclamaciones adicionales que se agregarán al token.
     * @param user: El UserDetails (detalles del usuario) para el cual se genera
     * el token.
     * @return El token JWT generado con las reclamaciones específicas.
     */
    public String getToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+8*60*60*1000))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    
    /**
     * Obtiene la clave secreta utilizada para firmar tokens JWT a partir de 
     * una clave en formato base64.
     * 
     * @return La clave secreta como un objeto Key.
     */
    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
     /**
     * Obtiene el nombre de usuario(subject) de un token JWT.
     * 
     * @param token: El token JWT del cual se extrae el nombre del usuario.
     * @return El nombre de usuario contenido en el token.
     */
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }
    
    /**
     * Verífica si un token JWT es válido para un UserDetails específico.
     * 
     * @param token: El token JWT que se va a verificar.
     * @param userDetails: Los detalles del usuario a comprar con el token.
     * @return true si el token es válido y corresponde al usuario; de lo 
     *         contrario false.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        
        final String userName = getUsernameFromToken(token);
        return (userName.equals(userDetails.getUsername()) && 
                !isTokenExpiration(token));
        
    }
    
    /**
     * Obtiene todas las reclamaciones(claims) de un token JWT.
     * 
     * @param token: El token JWT del cual se extraerán las reclamaciones.
     * @return Un objeto Claims que contiene todas las reclamaciones del token.
     */
    private Claims getAllClaims(String token) {
        
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        
    }
    
    /**
     * Obtiene una reclamación específica de un token JWT.
     * 
     * @param <T> El tipo de dato de la reclamación que se desea obtener.
     * @param token: El token JWT del cual se extraerá la reclamación.
     * @param claimsResolver: Una función que se específica cómo extraer la
     *                        reclamación deseada del objeto Claims.
     * @return El valor de la reclamación específica del token, convertido
     *         al tipo de dato especificado.
     */
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
        
    }
    
    /**
     * Obtiene la fecha de vencimiento(expiration) de un token JWT.
     * 
     * @param token: El token JWT del cual se obtendrá la fecha de vencimiento.
     * @return La fecha de vencimiento contenida en el token.
     */
    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }
    
    /**
     * Verifica si un token JWT ha expirado.
     * 
     * @param token: El token JWT que se verificará
     * @return true si el token ha expirado; de lo contrario, false.
     */
    private boolean isTokenExpiration(String token) {
        return getExpiration(token).before(new Date());
    }
}
