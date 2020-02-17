package FindBanana;

public class AStarStage implements Comparable {
	// Variable dictionary
	private int number_of_rooms;
	private int chair_room;
	private int monkey_room;
	private int g;
	private int h;
	private int f;
	private boolean can_move_left;
	private boolean can_move_right;
	private boolean can_push_left;
	private boolean can_push_right;
	private boolean on_chair;
	private boolean can_climb;
	private boolean can_grab;
	private boolean has_won;
	
	// Constructor
	public AStarStage(int number_of_rooms, 
			int monkey_room, 
			int chair_room, 
			int g, 
			boolean on_chair,
			boolean has_won) {
		
		// Initialize variables
		this.number_of_rooms = number_of_rooms;
		this.monkey_room = monkey_room;
		this.chair_room = chair_room;
		this.g = g;
		this.h = (number_of_rooms-monkey_room)+(number_of_rooms-chair_room);
		this.setF(this.g+this.h);
		this.on_chair = on_chair;
		this.setHas_won(has_won);

		// can_move_left
		if (this.monkey_room == 1) {
			this.can_move_left = false;
		} else {
			this.can_move_left = true;
		}
		// can_move_right
		if (this.monkey_room == this.number_of_rooms) {
			this.can_move_right = false;
		} else {
			this.can_move_right = true;
		}
		// can_push_left
		if (this.monkey_room == this.chair_room && this.monkey_room != 1) {
			this.can_push_left = true;
		} else {
			this.can_push_left = false;
		}
		// can_push_right
		if (this.monkey_room == this.chair_room && this.monkey_room != this.number_of_rooms) {
			this.can_push_right = true;
		} else {
			this.can_push_right = false;
		}
		// can_climb
		if (!this.on_chair && this.monkey_room == this.chair_room) {
			this.can_climb = true;
		} else {
			this.can_climb = false;
		}
		// can_grab
		if (this.on_chair && this.monkey_room == this.number_of_rooms) {
			this.can_grab = true;
		} else {
			this.can_grab = false;
		}

	}
	
	// 1 for Move
	// 5 for Push
	// 2 for Climb
	// 1 for Grab
	public AStarStage[] nextStages() {
		// Determine length
		int length = 0;
		if (can_move_left) { length += 1; }
		if (can_move_right) { length += 1; }
		if (can_push_left) { length += 1; }
		if (can_push_right) { length += 1; }
		if (can_climb) { length += 1; }
		if (can_grab) { length += 1; }
		
		AStarStage[] next = new AStarStage[length];
		
		// Add stages
		int incrementor = 0;
		if (can_move_left) {
			next[incrementor] = new AStarStage(number_of_rooms, monkey_room-1, chair_room, this.g+1, false, false);
			incrementor += 1;
		}
		if (can_move_right) {
			next[incrementor] = new AStarStage(number_of_rooms, monkey_room+1, chair_room, this.g+1, false, false);
			incrementor += 1;
		}
		if (can_push_left) {
			next[incrementor] = new AStarStage(number_of_rooms, monkey_room-1, chair_room-1, this.g+5, false, false);
			incrementor += 1;
		}
		if (can_push_right) {
			next[incrementor] = new AStarStage(number_of_rooms, monkey_room+1, chair_room+1, this.g+5, false, false);
			incrementor += 1;
		}
		if (can_climb) {
			next[incrementor] = new AStarStage(number_of_rooms, monkey_room, chair_room, this.g+2, true, false);
			incrementor += 1;
		}
		if (can_grab) {
			next[incrementor] = new AStarStage(number_of_rooms, monkey_room, chair_room, this.g+1, true, true);
			incrementor += 1;
		}
		
		return next;
	}
	
	public String toSting() {
		String full = "";
		// Create array of stage
		String[] array = new String[this.number_of_rooms];
		for (int i = 0; i < array.length; i++) {
			array[i] = "";
		}
		
		// Add banana, monkey, and chair
		array[this.number_of_rooms-1] = "B";
		// Add "+" if needed
		if (this.number_of_rooms==this.monkey_room) {
			array[this.number_of_rooms-1] += "+";
		}
		array[this.monkey_room-1] += "M";
		// Add "+" if needed
		if (this.number_of_rooms==this.chair_room || this.chair_room==this.monkey_room) {
			array[this.chair_room-1] += "+";
		}
		array[this.chair_room-1] += "C";
		
		// Print array
		for (int i = 0; i < array.length; i++) {
			if (array[i]!="") {
				full += array[i];
			} else {
				full += " ";
			}
			if (i!=array.length-1) {
				full += "|";
			}
		}
		
		// Add g, h, and f
		full += "\t\t";
		full += "g = " +this.g;
		full += "\t";
		full += "h = " +this.h;
		full += "\t";
		full += "f = " +this.getF();
		
		return full;
	}

	@Override
	public int compareTo(Object arg0) {
		return this.getF() - ((AStarStage)arg0).getF();
	}

	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}

	public boolean isHas_won() {
		return has_won;
	}

	public void setHas_won(boolean has_won) {
		this.has_won = has_won;
	}

}
