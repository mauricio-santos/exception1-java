package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
    private int roomNumber;
    private Date checkIn;
    private Date checkOut;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Reservation(int roomNumber, Date checkIn, Date checkOut) {
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public long duration(){
        //Método get.time() devolve quantidade de ms para uma data
        long dateMiliSeg = checkOut.getTime() - checkIn.getTime();

        //Convertendo milissegundos para dias.
        //Usamos a classe TimeUnit(Tipo enumerado complexo com algumas operações)
        return TimeUnit.DAYS.convert(dateMiliSeg, TimeUnit.MILLISECONDS);
    }

    public String updateDates(Date checkIn, Date checkOut){

        //Verificando se as reservas possuem uma data futura
        Date Now = new Date(); //criando uma data como hora e data atual
        if (checkIn.before(Now) || checkOut.before(Now)) {
            return "Error in reservation: Reservation dates for update must be future dates";
        } if (!checkOut.after(checkIn)) {
            return "Error in reservation: Check-out date must be after check-in date";
        }

        this.checkIn = checkIn;
        this.checkOut = checkOut;

        return null; //Indica que a operação não ocorreu erro
    }

    public String toString(){
        //Reservation: Room 8021, check-in: 23/09/2019, check-out: 26/09/2019, 3 nights
        return "Reservation: Room "
                + roomNumber
                + ", check-in: "
                + sdf.format(checkIn)
                + ", check-out: "
                + sdf.format(checkOut)
                + ", "
                + duration()
                + " nights";
    }
}
