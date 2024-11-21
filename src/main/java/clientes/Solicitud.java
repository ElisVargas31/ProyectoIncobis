package clientes;

public class Solicitud {
	 private String fecha;
	    private String estado;
	    private String cargo;
	    private int cliente_id;
	    private String experiencia;
	    private String tipo_de_contrato;
	    private String nivel_profesion;
	    private String comentario;
	    private String otros;
		public String getFecha() {
			return fecha;
		}
		public void setFecha(String fecha) {
			this.fecha = fecha;
		}
		public String getEstado() {
			return estado;
		}
		public void setEstado(String estado) {
			this.estado = estado;
		}
		public String getCargo() {
			return cargo;
		}
		public void setCargo(String cargo) {
			this.cargo = cargo;
		}
		public int getCliente_id() {
			return cliente_id;
		}
		public void setCliente_id(int cliente_id) {
			this.cliente_id = cliente_id;
		}
		public String getExperiencia() {
			return experiencia;
		}
		public void setExperiencia(String experiencia) {
			this.experiencia = experiencia;
		}
		public String getTipo_de_contrato() {
			return tipo_de_contrato;
		}
		public void setTipo_de_contrato(String tipo_de_contrato) {
			this.tipo_de_contrato = tipo_de_contrato;
		}
		public String getNivel_profesion() {
			return nivel_profesion;
		}
		public void setNivel_profesion(String nivel_profesion) {
			this.nivel_profesion = nivel_profesion;
		}
		public String getComentario() {
			return comentario;
		}
		public void setComentario(String comentario) {
			this.comentario = comentario;
		}
		public String getOtros() {
			return otros;
		}
		public void setOtros(String otros) {
			this.otros = otros;
		}
}
