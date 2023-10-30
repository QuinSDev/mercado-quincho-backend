package com.mercado.quincho.service;

import com.mercado.quincho.entity.Role;
import com.mercado.quincho.entity.User;
import com.mercado.quincho.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Servicio que gestiona las operaciones relacionadas con los usuarios en el
 * sistema. Esto incluye el registro de nuevos usuarios, así como la
 * modificación de usuarios esxistentes.
 *
 * @author QuinSDev
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Registra un nuevo usuario en el sistema con los detalles proporcionados.
     *
     * @param name: Nombre del usuario.
     * @param lastName: Apellido del usuario.
     * @param address: Dirección del usuario.
     * @param phoneNumber: Número de telefóno del usuario.
     * @param cellPhoneNumber: Número de telefóno celular del usuario.
     * @param email: Correo electrónico del usuario(debe ser único).
     * @param password: Contraseña del usuario.
     */
    @Transactional
    public void registerUser(String name, String lastName,
            String address, String phoneNumber, String cellPhoneNumber,
            String email, String password) {

        // Crea una instancia del usuario y establece los detalles proporcionados.
        User user = new User();

        user.setName(name);
        user.setLastName(lastName);
        user.setAddress(address);
        user.setPhoneNumber(phoneNumber);
        user.setCellPhoneNumber(cellPhoneNumber);
        user.setEmail(email);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setRol(Role.CUSTOMER);

        userRepository.save(user);

    }

    /**
     * Modífica los detalles de un usuario existente basado en su dirección de
     * correo electrónico.
     *
     * @param name: Nuevo nombre del usuario.
     * @param lastName: Nuevo apellido del usuario.
     * @param address: Nueva dirección del usuario.
     * @param phoneNumber: Nuevo número de teléfono del usuario.
     * @param cellPhoneNumber: Nuevo número de teléfono celular del usuario.
     * @param email: Dirección de correo electrónico del usuario para
     * identificarlo.
     * @param password: Nueva contraseña del usuario.
     */
    @Transactional
    public void updateUser(String name, String lastName,
            String address, String phoneNumber, String cellPhoneNumber,
            String email, String password) {
        
        // Busca un usuario existente por su dirección de correo electrónico
        Optional<User> response = userRepository.findByEmail(email);
        if (response.isPresent()) {
            /* Si se encuentra un usuario, actualiza sus detalles según los 
            nuevos valores.*/
            User user = response.get();
            user.setName(name);
            user.setLastName(lastName);
            user.setAddress(address);
            user.setPhoneNumber(phoneNumber);
            user.setCellPhoneNumber(cellPhoneNumber);
            user.setEmail(email);
            user.setPassword(new BCryptPasswordEncoder().encode(password));

            userRepository.save(user);
        }
    }
    
    /**
     * Recupera y devuelve una lista de todos los usuarios registrados en el
     * sistema.
     * 
     * @return Una lista de objetos de tipo User que representa a todos los 
     * usuarios.
     */
    @Transactional()
    public List<User> listUsers() {
        // Utiliza el repositorio para recuperar todos los usuarios registrados.
        return userRepository.findAll();
        
    }
}
