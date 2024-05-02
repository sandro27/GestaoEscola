package com.desafio.GestaoEscola.controller;

import com.desafio.GestaoEscola.dtos.PaisDTO;
import com.desafio.GestaoEscola.dtos.ProvinciaDTO;
import com.desafio.GestaoEscola.entities.Provincia;
import com.desafio.GestaoEscola.service.implementacao.ProvinciaServiceImpl;
import com.desafio.GestaoEscola.utils.ResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("provincia")
public class ProvinciaController extends BaseController {

    @Autowired
    private ProvinciaServiceImpl provinciaService;


    @Operation(description = "Lista todas as provincias existentes na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "retorna todos os  dados das provincias existentes na base de dados ou não retorna nenhum dado")
    })
    @GetMapping
    public ResponseEntity<ResponseBody> listar()
    {
        return this.ok("Provincias Listadas com sucesso",provinciaService.findAll());
    }

    @Operation(description = "Pesquisar Provincia por nome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "retorna a provincia que corresponde ao nome"),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos"),
            @ApiResponse(responseCode = "500", description = "se a rota estiver errada")
    })
    @GetMapping("/{nome}")
    public ResponseEntity<ResponseBody> pesquisarEspecifico(@PathVariable("nome") String nome)
    {
        return this.ok("Provincias Listadas com sucesso",provinciaService.pesquisarPorNome(nome));
    }
    @Operation(description = "Cadastrar uma Lista de provincias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Cadastra todas as provincias com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos"),
            @ApiResponse(responseCode = "500", description = "se a rota estiver errada")
    })
    @PostMapping
    public ResponseEntity<ResponseBody> criar( @RequestBody @Valid HashMap<String,PaisDTO> pais) throws JsonProcessingException {
        return this.created("Provincia Criada com sucesso",this.provinciaService.criarProvincia( pais));
    }

    @Operation(description = "Actualizar os dados de uma determinada provincia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Dados de uma provincia Actualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parametros Invalidos"),
            @ApiResponse(responseCode = "500", description = "se a rota estiver errada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponseBody> Editar(@RequestBody Provincia provincia, @PathVariable("id") UUID id)
    {
        return this.ok("Provincia Editado com sucesso",this.provinciaService.editar(id,provincia));
    }


    @Operation(description = "Deletar uma determinada provincia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Provincia deletada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos"),
            @ApiResponse(responseCode = "500", description = "se a rota estiver errada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBody> Deletar(@PathVariable("id") UUID id)
    {
        return this.ok("Provincia Eliminado com sucesso",this.provinciaService.eliminar(id));
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
