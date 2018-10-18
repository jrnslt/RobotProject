package nl.hva.miw.robot.cohort13;

public class SoundProducer {
	private Executable executable;
	
	public SoundProducer() {
		super();
		
		Runnable runnable = (new Runnable() {

			@Override
			public void run() {
				while (executable != null) {
					executable.execute();
					executable = null;
				}
			}
		});
		
		Thread thread = new Thread(runnable);
		thread.start();
	}
	
	public void setExecutable(Executable executable) {
		this.executable = executable;
	}
}
