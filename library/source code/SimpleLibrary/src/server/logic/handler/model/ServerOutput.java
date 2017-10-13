package server.logic.handler.model;

public class ServerOutput {
	String output;
	int state;
	public ServerOutput(String output,int state){
		this.output=output;
		this.state=state;
		
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	
	@Override
	public boolean equals(Object other)
	{
		ServerOutput otherOut = (ServerOutput) other;
		return (this.output.equals(otherOut.output) && this.state == otherOut.state);
	}
}
