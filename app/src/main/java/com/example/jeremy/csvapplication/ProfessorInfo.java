package com.example.jeremy.csvapplication;

class ProfessorInfo {
    String name;
    String office;
    String email;
    String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Professor " + name      + "\n\n" +
                "Phone: "   + phone     + "\n" +
                "Email: "   + email     + "\n" +
                "Office: "  + office    + "\n";
    }
}
