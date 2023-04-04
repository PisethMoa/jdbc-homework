package pakage2;
public class Topic{
    private Integer id;
    private String name;
    private String description;
    private boolean status;
    public Topic(){}
    public Topic(Integer id, String name, String description, boolean status){
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }
    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public boolean isStatus(){
        return status;
    }
    public void setStatus(boolean status){
        this.status = status;
    }
    @Override
    public String toString(){
        return "Topic{"+
                "id="+id+
                ", name='"+name+'\''+
                ", description='"+description+'\''+
                ", status="+status+
                '}';
    }
}
