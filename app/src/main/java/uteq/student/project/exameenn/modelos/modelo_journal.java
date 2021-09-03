package uteq.student.project.exameenn.modelos;

public class modelo_journal {
    private String journal_id;
    private String portada;
    private String abbreviation;
    private String description;
    private String name;

    public modelo_journal()
    {

    }
    public modelo_journal(String journal_id, String portada, String abbreviation, String descripcion, String name) {
        this.journal_id = journal_id;
        this.portada = portada;
        this.abbreviation = abbreviation;
        this.description = descripcion;
        this.name = name;
    }
    public String getJournal_id() {
        return journal_id;
    }
    public void setJournal_id(String journal_id) {
        this.journal_id = journal_id;
    }
    public String getPortada() {
        return portada;
    }
    public void setPortada(String portada) {
        this.portada = portada;
    }
    public String getAbbreviation() {
        return abbreviation;
    }
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
