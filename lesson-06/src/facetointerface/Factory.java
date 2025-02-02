package facetointerface;

public class Factory {
    private Equipment equipment;

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public void work(){
        System.out.println("开始生产......");
        this.equipment.work();
    }
}
