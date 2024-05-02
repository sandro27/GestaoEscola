package com.desafio.GestaoEscola.controller;

import com.desafio.GestaoEscola.entities.Escola;
import com.desafio.GestaoEscola.entities.Provincia;
import com.desafio.GestaoEscola.service.implementacao.EscolaServiceImpl;
import com.desafio.GestaoEscola.service.implementacao.ProvinciaServiceImpl;
import com.desafio.GestaoEscola.utils.ResponseBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("escola")
public class EscolaController extends BaseController {

    @Autowired
    private EscolaServiceImpl escolaService;

    @Operation(description = "Lista todas as escolas existentes na base de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "retorna todos os  dados das escolas existentes na base de dados ou não retorna nenhum dado"),
            @ApiResponse(responseCode = "400", description = "se a rota estiver errada")
    })
    @GetMapping
    public ResponseEntity<ResponseBody> listar()
    {
        return this.ok("Escolas Listadas com sucesso", escolaService.findAll());
    }

    @Operation(description = "Lista uma escola especifica com base no nome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "retorna a escola que corresponde ao nome"),
            @ApiResponse(responseCode = "500", description = "se a rota estiver errada")
    })
    @GetMapping("/{nome}")
    public ResponseEntity<ResponseBody> pesquisarespecifico(@PathVariable("nome") String nome)
    {
        return this.ok("Provincias Listadas com sucesso",escolaService.pesquisarPorNome(nome));
    }

    @Operation(description = "Cadastro de todas as escolas apartir do Excel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Cadastra todas as escolas com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos"),
            @ApiResponse(responseCode = "500", description = "se a rota estiver errada")
    })
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseBody> criar(@Valid @RequestPart MultipartFile file) throws JsonProcessingException {
        return this.created("Escolas Criadas com sucesso",this.escolaService.salvarEscola(file));
    }

    @Operation(description = "Editar uma escola com base no UUI")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Dados da Escola Actualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parametros Invalidos"),
            @ApiResponse(responseCode = "500", description = "se a rota estiver errada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponseBody> Editar(@RequestBody Escola escola, @PathVariable("id") UUID id)
    {
        return this.ok("Escola Editado com sucesso",this.escolaService.editar(id,escola));
    }

    @Operation(description = "Deletar uma escola especifica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Escola deletado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parametros invalidos"),
            @ApiResponse(responseCode = "500", description = "se a rota estiver errada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBody> Deletar(@PathVariable("id") UUID id)
    {
        return this.ok("Escola Eliminada com sucesso",this.escolaService.eliminar(id));
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
