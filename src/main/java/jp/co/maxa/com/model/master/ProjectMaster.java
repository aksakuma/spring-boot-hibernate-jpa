package jp.co.maxa.com.model.master;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import jp.co.maxa.com.context.AbstractRepository;
import jp.co.maxa.com.context.BaseEntity;
import jp.co.maxa.com.context.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/** サンプルエンティティを表現します。 */
@Entity
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class ProjectMaster extends BaseEntity<ProjectMaster> {
	static final long serialVersionUID = 1l;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String budget;
	private String description;
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDate;

	public static Optional<ProjectMaster> get(final AbstractRepository rep, final Long id){
		return rep.get(ProjectMaster.class, id);
	}

	public static ProjectMaster load(final AbstractRepository rep, final String id) {
        return rep.load(ProjectMaster.class, id);
    }

	public static ProjectMaster register(AbstractRepository rep, ProjectMasterRegister p){
		return p.register().save(rep);
	}

	public static List<ProjectMaster> fildAll(final AbstractRepository rep) {
		List<ProjectMaster> hoge = rep.findAll(ProjectMaster.class);
		System.out.println(hoge);
		return hoge;
    }

	  @Data
	  @NoArgsConstructor
	  @AllArgsConstructor
	  public static class ProjectMasterRegister implements Dto{
	    static final long serialVersionUID = 1l;
	    private String name;
	    private String budget;
	    private String description;
	    private Date startDate;
	    private Date endDate;
	    private ProjectMaster register(){
	      ProjectMaster m = new ProjectMaster();
	      m.setName(name);
	      m.setBudget(budget);
	      m.setDescription(description);
	      m.setStartDate(startDate);
	      m.setEndDate(endDate);
	      return m;
	    }
	  }
}