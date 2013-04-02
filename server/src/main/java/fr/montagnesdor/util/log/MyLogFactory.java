package fr.montagnesdor.util.log;

public interface MyLogFactory
{
    public MyLog getLogger(String name);
	public MyLog getLogger();
}