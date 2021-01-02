package pl.mateusz.kalksztejn.STM.services.Interfaces;

import pl.mateusz.kalksztejn.STM.models.Task;
import pl.mateusz.kalksztejn.STM.models.enums.Status;
import pl.mateusz.kalksztejn.STM.models.enums.Type;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    /***
     * F)
     * metoda do tworzenia nowego zadania przez użytkownika
     * @param task
     */
    Task create(Task task);

    /***
     * G)
     * metoda pobierająca wszystkie zadania uporządkowane po dacie dodania malejąco
     */
    List<Task> getAll();

    /***
     * H)
     * metoda wyszukująca zadania po nazwie, statusie lub typie
     * @param title
     * @param type
     * @param status
     */
    List<Task> getByTitleOrStatusOrType(String title, Type type, Status status);

    /***
     * metoda zwraca opcjonalne zadanie po Id
     * @param taskId
     */
    Optional<Task> getById(int taskId);

    /***
     * I)
     * metoda zmieniająca status zadania na inny dozwolony status
     * @param taskId
     * @param status
     */
    Task changeStatus(int taskId, Status status);

    /***
     * J)
     * metoda usuwająca zadanie, ale nie usuwająca użytkownika przypisanego do zadania
     * @param taskId
     */
    boolean deleteById(int taskId);

}
