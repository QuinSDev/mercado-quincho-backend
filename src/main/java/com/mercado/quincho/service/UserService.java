package com.mercado.quincho.service;

import com.mercado.quincho.entity.PhotoUser;
import com.mercado.quincho.entity.User;
import com.mercado.quincho.exception.MyException;
import com.mercado.quincho.repository.UserRepository;
import com.mercado.quincho.request.RegisterRequest;
import com.mercado.quincho.response.AuthResponse;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author QuinSDev
 */
@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final PhotoUserService photoUserService;

    public User getOne(String email) {
        return userRepository.buscarPorEmail(email);
    }

    @Transactional
    public List<User> listUser() {
        List<User> users = userRepository.findAll();

        return users;
    }

    @Transactional
    public AuthResponse updateUser(RegisterRequest request) {

        try {
            Optional<User> respuesta = userRepository.findByEmail(request.getEmail());
            if (respuesta.isPresent()) {

                User user = respuesta.get();
                validUser(request);

                String idPhoto = null;

                if (user.getPhoto() != null) {
                    idPhoto = user.getPhoto().getIdPhoto();
                }

                PhotoUser photoUser = photoUserService.updatePhotoUser(request.getFile(), idPhoto);

                // Crea un objeto User con los datos proporcionados por el usuario.
                user.setEmail(request.getEmail());
                user.setName(request.getName());
                user.setLastName(request.getLastName());
                user.setPassword(passwordEncoder.encode(request.getPassword()));
                user.setAddress(request.getAddress());
                user.setPhoneNumber(request.getPhoneNumber());
                user.setRole(user.getRole());
                user.setPhoto(photoUser);

                // Guarda el usuario en la base de datos.
                userRepository.save(user);

                // Devuelve el token de auntenticación para el usuario registrado.
                return AuthResponse.builder()
                        .msg("¡Actualizastes correctamente tus datos!")
                        .token(jwtService.getToken(user, user))
                        .build();
            }

        } catch (MyException ex) {
            return AuthResponse.builder()
                    .success(false)
                    .msg(ex.getMessage())
                    .build();
        }
        return null;
    }
    
    public void disableUser(String userId) {
        // Lógica para deshabilitar al usuario con el ID proporcionado
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        user.setActive(false); // Suponiendo que el campo que controla la activación es "active"
        userRepository.save(user);
    }
    
    public void activateUser(String userId) {
        // Lógica para deshabilitar al usuario con el ID proporcionado
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        user.setActive(true); // Suponiendo que el campo que controla la activación es "active"
        userRepository.save(user);
    }

    /**
     * Realiza la validación de los datos de registro.
     *
     * @param request: La solicitud de registro que se va a validar.
     * @throws MyException: Si se encuentran problemas en los datos de registro,
     * se lanza una excepción con un mensaje descriptivo del problema.
     */
    @Transactional
    public void validUser(RegisterRequest request) throws MyException {
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new MyException("El nombre no puede estar vacío");
        }

        if (request.getLastName() == null || request.getLastName().trim().isEmpty()) {
            throw new MyException("El apellido no puede estar vacío");
        }
        if (request.getAddress() == null || request.getAddress().trim().isEmpty()) {
            throw new MyException("La dirección no puede estar vacía o ser nula");
        }
        if (request.getPhoneNumber() == null || request.getPhoneNumber().trim().isEmpty()) {
            throw new MyException("El número de teléfono no puede estar vacío"
                    + " o estar nulo");
        }
        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            throw new MyException("El email no puede estar vacío");
        }
        if (request.getPassword().length() <= 5) {
            throw new MyException("La contraseña no debe tener menos de 5 caracteres");
        }
        if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            throw new MyException("La contraseña no puede estar vacía");
        }
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new MyException("Las contraseñas no coinciden");
        }

    }

}
