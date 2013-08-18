package mekanism.client;

import java.util.List;

import mekanism.api.Object3D;
import mekanism.common.FluidNetwork.NetworkFinder;
import mekanism.common.IMechanicalPipe;
import mekanism.common.IUniversalCable;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

public class FluidClientUpdate
{
	public NetworkFinder finder;
	
	public World worldObj;
	
	public FluidStack fluidStack;

	public FluidClientUpdate(TileEntity head, FluidStack fluid)
	{
		worldObj = head.worldObj;
		fluidStack = fluid;
		finder = new NetworkFinder(head.worldObj, Object3D.get(head));
	}
	
	public void clientUpdate()
	{
		List<Object3D> found = finder.exploreNetwork();
		
		for(Object3D object : found)
		{
			TileEntity tileEntity = object.getTileEntity(worldObj);
			
			if(tileEntity instanceof IMechanicalPipe)
			{
				((IMechanicalPipe)tileEntity).onTransfer(fluidStack);
			}
		}
	}
}