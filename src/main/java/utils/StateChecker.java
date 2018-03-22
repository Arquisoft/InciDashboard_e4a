package utils;

import com.uniovi.InciDashboard_e4a.entities.State;

public class StateChecker {
	public static State getState(Long num) {
		switch(num.byteValue()) {
			case 1: return State.OPEN;
			case 2: return State.IN_PROCESS;
			case 3: return State.CLOSED;
			case 4 : return State.CANCELLED;
			default : return State.OPEN;
		}
	}
}
