package FindBanana;

import java.util.PriorityQueue;

public class Monkey {
	
	public static void main(String[] args) {
		System.out.println("AStar:");
		findBananasAStar(4,4,4);
		System.out.println("BFS:");
		findBananasBFS(4,4,4);
	}

	// f(n) = g(n) + h(n)
	// g(n) is the cost so far
	// h(n) is the estimate to get to the goal	
	public static void findBananasAStar(int number_of_rooms, int monkey_room, int chair_room) {
		
		// create root stage
		AStarStage root = new AStarStage(number_of_rooms, monkey_room, chair_room, 0, false, false);
		
        PriorityQueue<AStarStage> pQueue = new PriorityQueue<AStarStage>();
        pQueue.add(root);
        
        while(!pQueue.peek().isHas_won()) {
        	
            for(AStarStage next: pQueue.peek().nextStages()) {
            	pQueue.offer(next);
            }

            // print
            for (AStarStage i: pQueue) {
                System.out.println(i.toSting());
            }
            System.out.println();
            pQueue.poll();
                        
        }
        System.out.println(pQueue.peek().toSting());
	}
	
	// f(n) = g(n) + h(n)
	// g(n) is the cost so far
	// h(n) is 0
	public static void findBananasBFS(int number_of_rooms, int monkey_room, int chair_room) {
        
		BFSStage root = new BFSStage(number_of_rooms, monkey_room, chair_room, 0, false, false);
		
		PriorityQueue<BFSStage> pQueue = new PriorityQueue<BFSStage>();
        pQueue.add(root);
        
        while(!pQueue.peek().isHas_won()) {
        	
            for(BFSStage next: pQueue.peek().nextStages()) {
            	pQueue.offer(next);
            }
            
            // print
            for (BFSStage i: pQueue) {
                System.out.println(i.toSting());
            }
            System.out.println();
            pQueue.poll();
            
        }
        System.out.println(pQueue.peek().toSting());
	}

}
