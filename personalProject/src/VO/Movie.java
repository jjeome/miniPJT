package VO;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movie {
	private String movieName;
	private String movieDate;
	private String movieGenre;
	private String movieNation;
	private String movieKeyword;
	private int abled;

	@Override
	public String toString() {
		return "[영화 : " + movieName + ", 개봉일 : " + movieDate + ", 영화 장르 : " + movieGenre
				+ ", 국가 : " + movieNation + "]\n";
	}
	
}
