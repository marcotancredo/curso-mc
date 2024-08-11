package com.marcotancredo.cursomc.services.validation;

import com.marcotancredo.cursomc.domain.enums.TipoCliente;
import com.marcotancredo.cursomc.dto.ClienteNewDTO;
import com.marcotancredo.cursomc.resources.exceptions.FieldMessage;
import com.marcotancredo.cursomc.services.validation.utils.BR;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Override
    public void initialize(ClienteInsert amm) {
    }

    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (TipoCliente.PESSOA_FISICA.getCod() == objDto.getTipo() && !BR.isValidCPF(objDto.getCpfCnpj())) {
            list.add(new FieldMessage("cpfCnpj","CPF Inválido"));
        }

        if (TipoCliente.PESSOA_JURIDICA.getCod() == objDto.getTipo() && !BR.isValidCPF(objDto.getCpfCnpj())) {
            list.add(new FieldMessage("cpfCnpj","CNPJ Inválido"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }
}
