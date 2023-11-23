package com.mercado.quincho.service;

import com.mercado.quincho.entity.CustomerOpinion;
import com.mercado.quincho.entity.Quincho;
import com.mercado.quincho.entity.User;
import com.mercado.quincho.repository.CustomerOpinionRepository;
import com.mercado.quincho.repository.QuinchoRepository;
import com.mercado.quincho.repository.UserRepository;
import com.mercado.quincho.request.OpinionRequest;
import com.mercado.quincho.response.QuinchoResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author EdwarVelasquez
 */
@Service
@RequiredArgsConstructor
public class CustomerOpinionService {

    @Autowired
    private final CustomerOpinionRepository customerOpinionRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final QuinchoRepository quinchoRepository;

    @Transactional
    public QuinchoResponse registerOpinion(String idUser, String idQuincho, OpinionRequest request) {
        try {
            Optional<User> responseUser = userRepository.findByEmail(idUser);

            if (responseUser.isPresent()) {

                Optional<Quincho> responseQuincho = quinchoRepository.findById(idQuincho);

                if (responseQuincho.isPresent()) {
                    User user = responseUser.get();
                    Quincho quincho = responseQuincho.get();

                    CustomerOpinion customerOpinion = new CustomerOpinion();

                    customerOpinion.setComment(request.getComment());
                    customerOpinion.setUser(user);
                    customerOpinion.setQuincho(quincho);
                    user.getOpinions().add(customerOpinion);
                    quincho.getOpinions().add(customerOpinion);

                    customerOpinionRepository.save(customerOpinion);
                    userRepository.save(user);
                    quinchoRepository.save(quincho);

                    return QuinchoResponse.builder()
                            .msg("Tu comentario fue exitoso")
                            .build();
                }
            }
        } catch (Exception e) {
            return QuinchoResponse.builder()
                    .msg("Error al registrar comentario: " + e.getMessage())
                    .build();
        }
        return null;
    }

    @Transactional
    public List<CustomerOpinion> getListOpinionsQuincho(String idQuincho) {
        Optional<Quincho> responseQuincho = quinchoRepository.findById(idQuincho);

        if (responseQuincho.isPresent()) {
            Quincho quincho = responseQuincho.get();
            List<CustomerOpinion> customerOpinions = quincho.getOpinions();
            return customerOpinions;

        }
        return null;
    }
    
    @Transactional
    public User getUserComentary(String idComentary) {
        Optional<CustomerOpinion> responseC = customerOpinionRepository.findById(idComentary);
        
        if (responseC.isPresent()) {
            CustomerOpinion customerOpinion = responseC.get();
            
            User user = customerOpinion.getUser();
            return user;
        }
        return null;
    }

}
