package com.io.github.annadrumond.springbasic.services;

//https://acervolima.com/introducao-ao-spring-boot/

import com.io.github.annadrumond.springbasic.entities.User;
import com.io.github.annadrumond.springbasic.repositories.UserRepository;
import com.io.github.annadrumond.springbasic.services.exceptions.DatabaseException;
import com.io.github.annadrumond.springbasic.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //Buscar lista de todos os users na bd H2
    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        //https://medium.com/@danielchristofolli/optional-java-f5fad98fa247
        //http://mauda.com.br/?p=1383
        //https://www.baeldung.com/java-optional
        Optional<User> userOptional = userRepository.findById(id);
        // O método get() abaixo é da classe Optional e vai retonar o objeto User armazenado em userOptional
        // Se busco algo que não existe na minha BD H2, o Optional fica com valor EMPTY
        if (userOptional.isEmpty()){
            throw new ResourceNotFoundException(id);
        }
        // orElseThrow() vai tentar obter o objeto armazenado dentro do Optional, caso não exista um objeto
        // será lançada a exceção
        return userOptional.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    //Inserir novos utilizadores e retornar o User adicionado na bd
    public User insertUser(User user){
        return userRepository.save(user);// este método save() de JPARepository por default já retorna o objeto salvo
    }

    public void deleteUser(Long idUser){
        try {
            userRepository.deleteById(idUser);//  método de JPARepository

        } catch (EmptyResultDataAccessException emptyResultDataAccessException){
            throw new ResourceNotFoundException(idUser);

        } catch (DataIntegrityViolationException dataIntegrityViolationException){
            throw new DatabaseException(dataIntegrityViolationException.getMessage());
        };
    }

    public User updateUser(Long idUser, User userNewData){
        try {
            //https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html
            //getReferenceById(ID id)
            //Retorna uma referência à entidade com o identificador fornecido.
            User userUpdated = userRepository.getReferenceById(idUser);// Não vai a bd
            updateDataUser(userUpdated, userNewData);
            return userRepository.save(userUpdated);

        }catch (EntityNotFoundException entityNotFoundException){
            throw new ResourceNotFoundException(idUser);
        }
    }

    private void updateDataUser(User userUpdated, User userNewData) {
        userUpdated.setName(userNewData.getName());
        userUpdated.setEmail(userNewData.getEmail());
        userUpdated.setPhone(userNewData.getPhone());
        //no momento não está permitido alterar a password
    }

}
