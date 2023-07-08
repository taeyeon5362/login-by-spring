package jpa.board.controller;

public class MemberForm {
    private String mid;
    private String psw;
    private String email;
    private String name;


    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getPsw() {
        return psw;
    }
    public void setPsw(String psw){
        this.psw = psw;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public void printAll(){
        System.out.println(mid +" "+psw+" "+name+" "+email);
    }
}
