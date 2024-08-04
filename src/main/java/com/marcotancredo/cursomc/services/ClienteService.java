package com.marcotancredo.cursomc.services;

import com.marcotancredo.cursomc.domain.Cliente;
import com.marcotancredo.cursomc.dto.ClienteDTO;
import com.marcotancredo.cursomc.repositories.ClienteRepository;
import com.marcotancredo.cursomc.services.exceptions.DataIntegrityException;
import com.marcotancredo.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente find(Long id) {
        Optional<Cliente> retorno = repository.findById(id);

        return retorno.orElseThrow(() -> new ObjectNotFoundException(id, Cliente.class));
    }

    public Cliente insert(Cliente obj) {
        return repository.save(obj);
    }

    public void update(Cliente obj) {
        Cliente newObj = find(obj.getId());
        updateData(newObj, obj);
        insert(obj);
    }

    public void delete(Long id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityException("Não é possível excluir o cliente porque existe entidades relacionadas");
        }

    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO objDTO) {
        return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
}
