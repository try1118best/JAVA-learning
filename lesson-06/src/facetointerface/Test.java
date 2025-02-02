package facetointerface;

public class Test {
    public static void main1(String[] args) {
        //EquipmentA equipmentA = new EquipmentA();
        EquipmentB equipmentB = new EquipmentB();
        Factory factory = new Factory();
        //factory.setEquipmentA(equipmentA );//把设备注入到工厂里面
        //factory.setEquipment(equipmentA);
        factory.setEquipment(equipmentB);
        factory.work();
    }
}
