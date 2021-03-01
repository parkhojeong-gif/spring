package com.company.yedam.di;

public class SamsungTV implements TV {
	
	Speaker speaker;
	public SamsungTV() {}
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
	public SamsungTV(Speaker speaker) {
		this.speaker = speaker;
	}
	public void powerOn() {
		System.out.println("SamsungTV powerOn");
	}
	public void powerOff() {
		System.out.println("SamsungTV powerDown");
	}
	public void volumeUp() {
		speaker.volumeUp();
	}
	public void volumeDown() {
		speaker.volumeDown();
	}	
}
