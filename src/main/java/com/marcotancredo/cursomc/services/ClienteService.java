package com.marcotancredo.cursomc.services;

import com.marcotancredo.cursomc.domain.Cidade;
import com.marcotancredo.cursomc.domain.Cliente;
import com.marcotancredo.cursomc.domain.Endereco;
import com.marcotancredo.cursomc.domain.enums.TipoCliente;
import com.marcotancredo.cursomc.dto.ClienteDTO;
import com.marcotancredo.cursomc.dto.ClienteNewDTO;
import com.marcotancredo.cursomc.repositories.CidadeRepository;
import com.marcotancredo.cursomc.repositories.ClienteRepository;
import com.marcotancredo.cursomc.repositories.EnderecoRepository;
import com.marcotancredo.cursomc.services.exceptions.DataIntegrityException;
import com.marcotancredo.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente find(Long id) {
        Optional<Cliente> retorno = repository.findById(id);

        return retorno.orElseThrow(() -> new ObjectNotFoundException(id, Cliente.class));
    }

    @Transactional
    public Cliente insert(Cliente obj) {
        Cliente saved = repository.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return saved;
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

    public Cliente fromDTO(ClienteNewDTO objDTO) {
        Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfCnpj(), TipoCliente.toEnum(objDTO.getTipo()));
        Cidade cid = cidadeRepository.findById(objDTO.getCidadeId()).orElse(null);
        Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, cid);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(objDTO.getTelefone1());
        if (objDTO.getTelefone2() != null) {
            cli.getTelefones().add(objDTO.getTelefone2());
        }

        if (objDTO.getTelefone3() != null) {
            cli.getTelefones().add(objDTO.getTelefone3());
        }

        return cli;
    }
}
