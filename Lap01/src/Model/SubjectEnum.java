/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Model;

/**
 *
 * @author THUYVU
 */
public enum SubjectEnum {
    MOB1014("Lap trinh java 1"), MOB1023("Lap trinh java 2"), SOF203("Lap trinh java 3"),
    SOF3011("Lap trinh java 4"), SOF302("Lap trinh java 5"), SOF306("Lap trinh java 6"),
    COM1014("Tin hoc co so"), COM108("Co so du lieu"),
    COM2012("Quann tri co so du lieu voi SQL server");
    
    String subjectName;

    private SubjectEnum() {
    }

    private SubjectEnum(String subjectName) {
        this.subjectName = subjectName;
    }
    
    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    
    public static SubjectEnum getCode(String name) {
            for (SubjectEnum x : SubjectEnum.values() ) {
                if (x.subjectName == name ) {
                    return x;
                }
            }
            return null;
        }
}
