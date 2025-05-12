package Modelos;

public class Persona {
    

    String Dni;
    String nombre;
    String apellido; 
    String rol;
    String email;
    String telefono;
    String contraseña;
    
   

    public Persona(String dNI, String nombre, String apellido, String rol, String email, String telefono,
            String contraseña) {
        Dni = dNI;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.email = email;
        this.telefono = telefono;
        this.contraseña = contraseña;
    }

    public Persona() {

    }

    public String getDNI() {
        return Dni;
    }

    public void setDNI(String dNI) {
        Dni = dNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "Persona [DNI=" + Dni + ", nombre=" + nombre + ", apellido=" + apellido + ", rol=" + rol + ", email="
                + email + ", telefono=" + telefono + ", contraseña=" + contraseña + "]";
    }

}