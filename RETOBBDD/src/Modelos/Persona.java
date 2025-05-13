package Modelos;

public abstract class Persona {
    

    String Dni;
    String nombre;
    String apellido; 
    String rol;
    String email;
    String telefono;
    String contrasena;
    
   

    public Persona(String Dni, String nombre, String apellido, String rol, String email, String telefono,
            String contrasena) {
        this.Dni = Dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
        this.email = email;
        this.telefono = telefono;
        this.contrasena = contrasena;
    }

    public Persona() {

    }

    public String getDNI() {
        return Dni;
    }

    public void setDNI(String Dni) {
      this.Dni = Dni;
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "Persona [DNI=" + Dni + ", nombre=" + nombre + ", apellido=" + apellido + ", rol=" + rol + ", email="
                + email + ", telefono=" + telefono + ", contraseña=" + contrasena + "]";
    }

}