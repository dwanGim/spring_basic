package com.ict.di.classfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Satellite {
	
	
	private Broadcast broad;
	
	public Satellite(Broadcast broad) {
		this.broad = broad;
	}
	
	public void SatelliteBroad() {
		System.out.print("위성  ");
		broad.broad();
	}
	
}
