package it.uniroma3.icr.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Job {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(nullable=false)
	private String description;
	
	@Column(nullable=false)
	private int taskSize;
	
	@Column(nullable = false)
	private int numberOfImages;
	
	@Column(nullable=false)
	private int percentageType1;
	
	@Column(nullable=false)
	private int percentageType2;
	
	@Column(nullable=false)
	private int percentageType3;
	
	@Column(nullable=false)
	private int numberOfStudents;
	
	@Column(nullable=false)
	private String imageManuscript;
	
	@ManyToMany
	List<Image> images;
	
	@OneToMany(mappedBy="job")
	List<Task> tasks; 
	
	@ManyToOne
	private Symbol symbol;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Symbol getSymbol() {
		return symbol;
	}

	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}

	public int getNumberOfImages() {
		return numberOfImages;
	}

	public void setNumberOfImages(int numberOfImages) {
		this.numberOfImages = numberOfImages;
	}

	public int getPercentageType1() {
		return percentageType1;
	}

	public void setPercentageType1(int percentageType1) {
		this.percentageType1 = percentageType1;
	}

	public int getPercentageType2() {
		return percentageType2;
	}

	public void setPercentageType2(int percentageType2) {
		this.percentageType2 = percentageType2;
	}

	public int getPercentageType3() {
		return percentageType3;
	}

	public void setPercentageType3(int percentageType3) {
		this.percentageType3 = percentageType3;
	}

	public int getNumberOfStudents() {
		return numberOfStudents;
	}

	public void setNumberOfStudents(int numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
	}

	public int getStudents() {
		return numberOfStudents;
	}

	public void setStudents(int students) {
		this.numberOfStudents = students;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTaskSize() {
		return taskSize;
	}

	public void setTaskSize(int taskSize) {
		this.taskSize = taskSize;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	
	
	
	public Job(Long id, String title, int taskSize, 
			int numberOfImages, int percentageType1, int percentageType2,
			int percentageType3, int numberOfStudents, 
			List<Image> images, List<Task> tasks, 
			Symbol symbol) {
		super();
		this.id = id;
		this.title = title;
		this.taskSize = taskSize;
		this.numberOfImages = numberOfImages;
		this.percentageType1 = percentageType1;
		this.percentageType2 = percentageType2;
		this.percentageType3 = percentageType3;
		this.numberOfStudents = numberOfStudents;
		this.images = images;
		this.tasks = tasks;
		this.symbol = symbol;
	}
	
	

	public String getImageManuscript() {
		return imageManuscript;
	}

	public void setImageManuscript(String imageManuscript) {
		this.imageManuscript = imageManuscript;
	}

	public Job() {
		
	}

}
