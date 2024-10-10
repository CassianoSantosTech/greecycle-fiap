package br.com.fiap.greencycle.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Anotação "Document" é usada para indicar que esta classe será mapeada para uma coleção no MongoDB.
// A propriedade collection especifica o nome da coleção.
@Document(collection = "coleta_extra") 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ColetaExtra {
	
	@Id  // Anotação para o campo identificador no MongoDB
    private String idColetaE;  // MongoDB usa String como o tipo de ID por padrão
	
	@JsonProperty("dataColeta")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Field("dt_coleta")
    private LocalDate dataColeta;
	
// Anotação é usada para mapear campos Java para campos específicos dentro do documento MongoDB, substituindo a anotação @Column.	
	@JsonProperty("idUsuario")
	@Field("id_usuario")
    private Long idUsuario;
	
	@JsonProperty("idQrCode")
	@Field("id_qrcode")
    private Long idQrCode;
	
	@JsonProperty("status")
    private String status;
	
	@JsonProperty("endereco")
	@Field("endereco")
	private String endereco;
	
	@JsonProperty("telefone")
	@Field("telefone")
	private String telefone;
	
	@JsonProperty("cpf")
	@Field("cpf")
	private String cpf;
	
	@JsonProperty("cnpj")
	@Field("cnpj")
	private String cnpj;
	
	public LocalDate getDataColeta() {
        return dataColeta;
    }

    public void setDataColeta(LocalDate dataColeta) {
        this.dataColeta = dataColeta;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
}
