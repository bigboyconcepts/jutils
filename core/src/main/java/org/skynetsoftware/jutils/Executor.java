package org.skynetsoftware.jutils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Predrag Čokulov*/

public class Executor
{
    private final ExecutorService executorService;
	private static Executor executor = null;
    private static List<Future<Runnable>> tasks;

    private Executor()
    {
        executorService = Executors.newCachedThreadPool();
        tasks = new ArrayList<>();
    }

	public static Executor getInstance()
	{
		if(executor == null)
		{
			executor = new Executor();
		}
        tasks.clear();//Im not sure about this
		return executor;
	}

    /**
     * Adds task to the que
     * @param runnable - runnable to be executed*/
    public Executor addTask(Runnable runnable)
    {
        Future f = executorService.submit(runnable);
        tasks.add(f);
        return executor;
    }

    /**
     * execute single runnable
     * @param runnable - runnable to be executed*/
    public void executeSingleTask(Runnable runnable)
    {
       executorService.execute(runnable);
    }

    /**
     * Executes all tasks currently in que and waits for all of them to finish before returning
     * */
    public void executeAll()
    {
        for (Future<Runnable> f : tasks)
        {
            try
            {
                f.get();
            }
            catch (InterruptedException | ExecutionException e)
            {
                if(Logging.LOGGING)e.printStackTrace();
            }
        }
    }

    
    public void shutdown()
    {
        executorService.shutdownNow();
    }
}
