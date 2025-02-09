package io.namoosori.travelclub.web.aggregate.club;

import com.google.gson.Gson;
import io.namoosori.travelclub.web.aggregate.Entity;
import io.namoosori.travelclub.web.shared.NameValue;
import io.namoosori.travelclub.web.shared.NameValueList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TravelClub extends Entity {
	//
	private static final int MINIMUM_NAME_LENGTH =  3;
	private static final int MINIMUM_INTRO_LENGTH =  10;

	private String name;
	private String intro;
	private long foundationTime;

	public TravelClub(String id) {
		//
		super(id);
	}

	public TravelClub(String name, String intro) {
		//
		super();
		this.name = name;
		this.intro = intro;
		this.foundationTime = System.currentTimeMillis();
	}

	@Override
	public String toString() {
		//
		StringBuilder builder = new StringBuilder();

		builder.append("Travel Club Id:").append(id);
		builder.append(", name:").append(name);
		builder.append(", intro:").append(intro);
		builder.append(", foundation day:").append(foundationTime);

		return builder.toString();
	}

	public void checkValidation() {
		//
		checkNameValidation(name);
		checkIntroValidation(intro);
	}

	private void checkNameValidation(String name) {
		//
		if (name.length() < TravelClub.MINIMUM_NAME_LENGTH) {
			throw new IllegalArgumentException("\t > Name should be longer than " + TravelClub.MINIMUM_NAME_LENGTH);
		}
	}

	private void checkIntroValidation(String intro) {
		//
		if (intro.length() < TravelClub.MINIMUM_INTRO_LENGTH) {
			throw new IllegalArgumentException("\t > Intro should be longer than " + TravelClub.MINIMUM_INTRO_LENGTH);
		}
	}

	public void modifyValues(NameValueList nameValues) {
		//
		for (NameValue nameValue : nameValues.getNameValues()) {
			String value = nameValue.getValue();
			switch (nameValue.getName()) {
				case "name":
					checkNameValidation(value);
					this.name = value;
					break;
				case "intro":
					checkIntroValidation(value);
					this.intro = value;
					break;
			}
		}
	}

	public static TravelClub sample() {
		//
		String name = "JavaTravelClub";
		String intro = "Travel club to the Java island.";

		return new TravelClub(name, intro);
	}

	public static void main(String[] args) {
		// 샘플 데이터 보기
		System.out.println(new Gson().toJson(sample()));
		NameValueList list = new NameValueList();
		list.addNameValue("name","Change Club name!!");
		list.addNameValue("intro","Change Club intro!!");
		System.out.println(new Gson().toJson(list));
	}
}
