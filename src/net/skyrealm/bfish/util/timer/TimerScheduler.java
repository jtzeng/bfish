/*
 * Bika's Fish is a re-make of an old Scratch project.
 * Copyright (C) 2012	Justin Zeng
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.skyrealm.bfish.util.timer;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Handles repetitive <code>Timer</code> events in the game.
 * @author justin.zeng1
 *
 */

public class TimerScheduler {
	
	private static final TimerScheduler singleton = new TimerScheduler();
	private static List<Timer> timers = new ArrayList<Timer>();
	
	private TimerScheduler() {
		/*
		 * private constructor to prevent instantiation.
		 */
	}
	
	/**
	 * Gets the singleton.
	 * @return
	 */
	public static TimerScheduler getSingleton() {
		return singleton;
	}
	
	/**
	 * Resets all the tasks. eg, call this during a restart.
	 */
	public static void resetTasks() {
		for (Timer t : timers) {
			t.cancel();
		}
	}
	
	/**
	 * Schedules a new task and puts it into
	 * a private <code>ArrayList</code>.
	 * @param timerTask
	 * @param interval
	 */
	public void scheduleNewTask(TimerTask timerTask, int interval) {
		Timer t = new Timer();
		t.schedule(timerTask, 0, interval);
		timers.add(t);
	}

}
