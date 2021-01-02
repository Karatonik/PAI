package pl.mateusz.kalksztejn.STM.services.Interfaces;

import pl.mateusz.kalksztejn.STM.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    /***
     * A)
     *metoda do tworzenia nowego użytkownika
     * @param user
     */
    User create(User user);

    /***
     * B)
     *metoda zwracająca wszystkich użytkowników
     */
    List<User> getAll();

    /***
     * C)
     * metoda zwracająca użytkownika wyszukanego na podstawie klucza głównego lub adresu email
     * @param userId
     * @param email
     */
    List<User> getByEmailOrId(Integer userId, String email);

    /***
     *metoda zwracająca użytkownika po ID
     * @param userId
     */
    Optional<User> getById(Integer userId);

    /***
     * D)
     * metoda zmieniająca status użytkownika na wartość przeciwną względem aktualnej
     * @param userId
     */
    boolean activate(int userId);

    /***
     * E)
     * metoda usuwająca użytkownika wraz z jego relacjami
     * @param userId
     */
    boolean deleteById(int userId);
}
