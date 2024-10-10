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

@Document(collection = "notificacoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Notificacoes {
	
	@Id
	private String idNotificacao;
	
	@JsonProperty("userEmail")
	@Field("user_email")
	private String userEmail;
	
	@JsonProperty("tpNotificacao")
	@Field("tp_notificacao")
	private String tpNotificacao;
	
	@Field("mensagem")
	private String mensagem;
	
	@JsonProperty("dataEnvio")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Field("dt_envio")
	private LocalDate dataEnvio;
	
	public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String string) {
        this.userEmail = string;
    }

    public String getTpNotificacao() {
        return tpNotificacao;
    }

    public void setTpNotificacao(String tpNotificacao) {
        this.tpNotificacao = tpNotificacao;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDate getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDate dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

}
