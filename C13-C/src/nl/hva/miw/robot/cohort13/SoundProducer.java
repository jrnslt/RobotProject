package nl.hva.miw.robot.cohort13;

public class SoundProducer {
	private Executable executable;
	private Thread thread;
	
	public SoundProducer() {
		super();
		
		Runnable runnable = (new Runnable() {

			@Override
			public void run() {
				while (true) {
					if (executable != null) {
						executable.execute();
						executable = null;
					}
				}
			}
		});
		
		thread = new Thread(runnable);
		thread.start();
	}
	
	public void setExecutable(Executable executable) {
		this.executable = executable;
	}
}
