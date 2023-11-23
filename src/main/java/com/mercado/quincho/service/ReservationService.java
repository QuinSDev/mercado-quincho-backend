package com.mercado.quincho.service;

import com.mercado.quincho.entity.Quincho;
import com.mercado.quincho.entity.Reservation;
import com.mercado.quincho.entity.User;
import com.mercado.quincho.exception.MyException;
import com.mercado.quincho.repository.QuinchoRepository;
import com.mercado.quincho.repository.ReservationRepository;
import com.mercado.quincho.repository.UserRepository;
import com.mercado.quincho.request.ReservationRequest;
import com.mercado.quincho.response.QuinchoResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Servicio encargado de gestionar las operaciones relacionadas con las
 * reservas. Proporciona métodos para crear, actualizar, listar y eliminar
 * reservas, además de realizar validaciones de datos antes de llevar a cabo
 * operaciones de registro o modificación.
 *
 * @author monte
 * @author QuinSDev
 */
@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private QuinchoRepository quinchoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private QuinchoService quinchoService;

    /**
     * Crea una reserva a partir de la información proporcionada en la
     * solicitud. Realiza validaciones de datos, busca el usuario y quincho
     * asociados y guarda la reserva en la base de datos.
     *
     * @param request: Datos de la reserva a crear
     * @param idUser: Identificador del usuario que realiza la reserva
     * @param idQuincho: Identificador del quincho reservado
     * @return Objeto QuinchoResponse con un mensaje indicando el resultado de
     * la operación
     */
    @Transactional
    public QuinchoResponse createReservation(ReservationRequest request,
            String idUser, String idQuincho) {

        try {
            Optional<User> responseUser = userRepository.findByEmail(idUser);
            if (responseUser.isPresent()) {

                Optional<Quincho> response = quinchoRepository.findById(idQuincho);

                if (response.isPresent()) {
                    User user = responseUser.get();
                    Quincho quincho = response.get();
                    validateReservation(request);

                    Reservation reservation = new Reservation();

                    reservation.setStartDate(request.getStartDateAsDateTime());
                    reservation.setEndDate(request.getEndDateAsDateTime());

                    reservation.setCheckIn(request.getCheckIn());
                    reservation.setCheckOut(request.getCheckOut());
                    reservation.setTotalPayment(request.getTotalPayment());
                    reservation.setGuest(request.getGuest());
                    reservation.setUser(user);
                    reservation.setQuincho(quincho);

                    user.getReservation().add(reservation);
                    quincho.getReservations().add(reservation);

                    reservationRepository.save(reservation);
                    userRepository.save(user);
                    quinchoRepository.save(quincho);

                    return QuinchoResponse.builder()
                            .msg("Tu reserva fue exitosa")
                            .build();
                }

            }

        } catch (MyException ex) {
            return QuinchoResponse.builder()
                    .msg("Error al reservar: " + ex.getMessage())
                    .build();
        }
        return null;
    }

    @Transactional
    public List<Reservation> listReservationUser(String idUser) {
        User user = userService.getOne(idUser);

        List<Reservation> reservations = user.getReservation();
        return reservations;
    }

    @Transactional
    public List<Reservation> listReservations(String idUser) {
        User user = userService.getOne(idUser);

        List<Quincho> quinchos = user.getQuincho();
        List<Reservation> reservations = new ArrayList();
        for (Quincho quincho : quinchos) {
            List<Reservation> quinchoReservations = quincho.getReservations();
            reservations.addAll(quinchoReservations);
        }
        return reservations;
    }

    /*@Transactional
    public List<Reservation> listReservationQuincho(String idQuincho) {
        Optional<Quincho> responseQuincho = quinchoService.getOne(idQuincho);
        if (responseQuincho.isPresent()) {
            Quincho quincho = responseQuincho.get();
            List<Reservation> reservations = quincho.getReservations();
            return reservations;
        }
        return null;
    }*/
    public Quincho finQuinchoByReservationId(String idReservation) {
        Optional<Reservation> reservationOptional = reservationRepository.
                findByIdReservation(idReservation);
        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();
            return reservation.getQuincho();
        }
        return null;
    }
    
    public User finUserByReservationId(String idReservation) {
         Optional<Reservation> reservationOptional = reservationRepository.
                findByIdReservation(idReservation);
        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();
            return reservation.getUser();
        }
        return null;
    }

    /**
     * Modífica los datos de una reservación existente basado en su id
     *
     * @param idReservation: identificador unico
     * @param startDate: Nueva fecha reserva
     * @param endDate: Finalización de la reserva
     * @param checkIn: Hora de ingreso
     * @param checkOut: Hora salida
     * @param totalPayment: Total a pagar
     * @param idQuincho: Identificador del Quincho
     */
    @Transactional
    public QuinchoResponse updateReservation(String idReservation, ReservationRequest request) {
        try {
            Optional<Reservation> existingReservation = reservationRepository.findById(idReservation);
            if (existingReservation.isPresent()) {
                Reservation reservation = existingReservation.get();
                // Actualiza los campos necesarios de la reserva , si existe

                reservation.setStartDate(request.getStartDateAsDateTime());
                reservation.setEndDate(request.getEndDateAsDateTime());

                reservation.setCheckIn(request.getCheckIn());
                reservation.setCheckOut(request.getCheckOut());
                reservation.setGuest(request.getGuest());
                reservation.setTotalPayment(request.getTotalPayment());

                reservationRepository.save(reservation);

                return QuinchoResponse.builder()
                        .msg("Tu reserva fue actualizada correctamente")
                        .build();
            }
        } catch (Exception e) {
            return QuinchoResponse.builder()
                    .msg("Error al actualizar la reserva: " + e.getMessage())
                    .build();
        }
        // Verifica si la reserva existe antes de actualizarla por su id
        return null;
    }

    /**
     * Recupera y devuelve una lista de todas las reservaciones registrados
     *
     * @return Una lista de objetos de tipo Reservation que representa a todos
     * las reservas
     */
    @Transactional
    public List<Reservation> listReservations() {
        return reservationRepository.findAll();
    }

    /**
     * Busca una reserva por su ID y devuelve la reserva si se encuentra
     *
     * @param idReservation: Identificador unico de la reserva
     * @return : Devuelve la reserva si se encuentra.
     * @throws MyException: Se lanza si se encuentran problemas con el id, con
     * un mensaje descriptivo del problema
     */
    @Transactional
    public Reservation getReservationById(String idReservation) throws MyException {
        Optional<Reservation> existingReservation = reservationRepository.findById(idReservation);
        if (existingReservation.isPresent()) {
            return existingReservation.get();
        } else {
            throw new MyException("La reserva con el ID proporcionado no se encontró.");
        }
    }

    /**
     * Elimina la resrva del sistema mediante su id
     *
     * @param idReservation: El identificador único de la reserva que se va a
     * eliminar
     */
    @Transactional
    public void deleteReservation(String idReservation) {
        reservationRepository.deleteById(idReservation);
    }

    /**
     * Valida los datos de una reserva antes de realizar una operación de
     * registro o modificación.Comprueba que los campos escenciales no estén
     * vacíos o nulos
     *
     * @param startDate: Fecha de inicio de la reserva
     * @param endDate: Fecha de finalización de la reserva
     * @param checkIn: Hora de ingreso
     * @param checkOut: Hora de salida
     * @param totalPayment: Total a pagar
     * @param idQuincho: Id del Quincho
     * @throws MyException: Se lanza si se encuentran problemas en los datos,
     * con un mensaje descriptivo del problema
     */
    @Transactional
    public void validateReservation(ReservationRequest request) throws MyException {

        if (request.getEndDateAsDateTime() == null) {
            throw new MyException("La fecha de inicio es obligatoria.");
        }

        if (request.getGuest() <= 0) {
            throw new MyException("L cantidad de personas de no puede ser menor"
                    + "o igual a 0");
        }

        /*if (request.getStarDate().isBefore(other)) {
            throw new MyException("La fecha de inicio no puede ser anterior"
                    + "a la fecha actual.");
        }*/
        if (request.getEndDateAsDateTime() == null) {
            throw new MyException("La fecha finalización es obligatoria.");
        }

        /*if (endDate.before(new Date())) {
            throw new MyException("La fecha finalización no puede ser anterior "
                    + "a la fecha actual.");
        }*/
        if (request.getCheckIn() == null) {
            throw new MyException("La hora de entrada es obligatoria.");
        }

        if (request.getCheckOut() == null) {
            throw new MyException("La hora de salida es obligatoria.");
        }

        if (request.getTotalPayment() == null || request.getTotalPayment() <= 0) {
            throw new MyException("El pago total es obligatorio.");
        }

    }
}
