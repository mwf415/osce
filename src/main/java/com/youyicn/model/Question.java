package com.youyicn.model;

import javax.persistence.*;

@Table(name = "osce_question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    /**
     * 描述
     */
    private String mark;

    /**
     * 考试时长
     */
    private Integer duration;

    /**
     * 分值
     */
    private Integer score;

    /**
     * 专业基地名称
     */
    @Column(name = "base_name")
    private String baseName;

    /**
     * 专业
     */
    @Column(name = "room_name")
    private String roomName;

    /**
     * 考题详情
     */
    @Column(name = "file_path")
    private String filePath;

    @Column(name = "answer")
    private String answer;
    
    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取描述
     *
     * @return mark - 描述
     */
    public String getMark() {
        return mark;
    }

    /**
     * 设置描述
     *
     * @param desc 描述
     */
    public void setMark(String mark) {
        this.mark = mark;
    }

    /**
     * 获取考试时长
     *
     * @return duration - 考试时长
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * 设置考试时长
     *
     * @param duration 考试时长
     */
    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    /**
     * 获取分值
     *
     * @return score - 分值
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 设置分值
     *
     * @param score 分值
     */
    public void setScore(Integer score) {
        this.score = score;
    }
    
	public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}
	
    public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	/**
     * 获取考题详情
     *
     * @return file_path - 考题详情
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 设置考题详情
     *
     * @param filePath 考题详情
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
    
}