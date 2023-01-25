package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Pitagoras implements Serializable {

	private static final long serialVersionUID = 1L;

	private String catetoA;
	private String catetoB;
	private String hipotenusa;
	
	public Pitagoras() {}
	
	public Pitagoras(String catetoA, String catetoB, String hipotenusa) {
		this.catetoA = catetoA;
		this.catetoB = catetoB;
		this.hipotenusa = hipotenusa;
	}

	public String getCatetoA() {
		return catetoA;
	}

	public void setCatetoA(String catetoA) {
		this.catetoA = catetoA;
	}

	public String getCatetoB() {
		return catetoB;
	}

	public void setCatetoB(String catetoB) {
		this.catetoB = catetoB;
	}

	public String getHipotenusa() {
		return hipotenusa;
	}

	public void setHipotenusa(String hipotenusa) {
		this.hipotenusa = hipotenusa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(catetoA, catetoB, hipotenusa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pitagoras other = (Pitagoras) obj;
		return Objects.equals(catetoA, other.catetoA) && Objects.equals(catetoB, other.catetoB)
				&& Objects.equals(hipotenusa, other.hipotenusa);
	}

	
	
}
