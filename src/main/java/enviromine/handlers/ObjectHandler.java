package enviromine.handlers;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.common.util.EnumHelper;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

import enviromine.EntityPhysicsBlock;
import enviromine.blocks.BlockElevatorBottom;
import enviromine.blocks.BlockElevatorTop;
import enviromine.blocks.BlockGas;
import enviromine.blocks.tiles.TileEntityElevatorBottom;
import enviromine.blocks.tiles.TileEntityElevatorTop;
import enviromine.blocks.tiles.TileEntityGas;
import enviromine.core.EM_Settings;
import enviromine.core.EnviroMine;
import enviromine.items.EnviroArmor;
import enviromine.items.EnviroItemBadWaterBottle;
import enviromine.items.EnviroItemColdWaterBottle;
import enviromine.items.EnviroItemSaltWaterBottle;
import enviromine.items.RottenFood;

public class ObjectHandler
{
	public static ArmorMaterial camelPackMaterial;
	
	public static Item badWaterBottle;
	public static Item saltWaterBottle;
	public static Item coldWaterBottle;
	
	public static Item airFilter;
	public static Item davyLamp;
	public static Item gasMeter;
	public static Item rottenFood;
	
	public static ItemArmor camelPack;
	public static ItemArmor gasMask;
	public static ItemArmor hardHat;
	
	public static Block elevatorTop;
	public static Block elevatorBottom;
	public static Block gasBlock;
	public static Block fireGasBlock;
	
	public static int renderGasID;
	
	public static void initItems()
	{
		badWaterBottle = new EnviroItemBadWaterBottle().setMaxStackSize(1).setUnlocalizedName("badwater").setCreativeTab(EnviroMine.enviroTab);
		saltWaterBottle = new EnviroItemSaltWaterBottle().setMaxStackSize(1).setUnlocalizedName("saltwater").setCreativeTab(EnviroMine.enviroTab);
		coldWaterBottle = new EnviroItemColdWaterBottle().setMaxStackSize(1).setUnlocalizedName("coldwater").setCreativeTab(EnviroMine.enviroTab);
		airFilter = new Item().setMaxStackSize(1).setUnlocalizedName("airfilter").setCreativeTab(EnviroMine.enviroTab).setTextureName("enviromine:air_filter");
		rottenFood = new RottenFood(1).setMaxStackSize(64).setUnlocalizedName("rottenfood").setCreativeTab(EnviroMine.enviroTab).setTextureName("enviromine:rot");
		
		camelPackMaterial = EnumHelper.addArmorMaterial("camelPack", 100, new int[]{1, 0, 0, 0}, 0);
		
		camelPack = (ItemArmor)new EnviroArmor(camelPackMaterial, 4, 1).setTextureName("camel_pack").setUnlocalizedName("camelpack").setCreativeTab(EnviroMine.enviroTab);
		gasMask = (ItemArmor)new EnviroArmor(camelPackMaterial, 4, 0).setTextureName("gas_mask").setUnlocalizedName("gasmask").setCreativeTab(EnviroMine.enviroTab);
		hardHat = (ItemArmor)new EnviroArmor(camelPackMaterial, 4, 0).setTextureName("hard_hat").setUnlocalizedName("hardhat").setCreativeTab(EnviroMine.enviroTab);
	}
	
	public static void registerItems()
	{
		GameRegistry.registerItem(badWaterBottle, "badWaterBottle");
		GameRegistry.registerItem(saltWaterBottle, "saltWaterBottle");
		GameRegistry.registerItem(coldWaterBottle, "coldWaterBottle");
		GameRegistry.registerItem(airFilter, "airFilter");
		GameRegistry.registerItem(rottenFood, "rottenFood");
		GameRegistry.registerItem(camelPack, "camelPack");
		GameRegistry.registerItem(gasMask, "gasMask");
		GameRegistry.registerItem(hardHat, "hardHat");
	}
	
	public static void initBlocks()
	{
		//elevator = new BlockElevator(EM_Settings.blockElevatorID, Material.iron);
		gasBlock = new BlockGas(EM_Settings.gasBlockID, Material.air).setBlockName("gas").setCreativeTab(EnviroMine.enviroTab);
		fireGasBlock = new BlockGas(EM_Settings.fireGasBlockID, Material.air).setBlockName("firegas").setCreativeTab(EnviroMine.enviroTab).setLightLevel(1.0F);
		
		elevatorTop = new BlockElevatorTop(Material.iron).setBlockName("elevator_top").setCreativeTab(EnviroMine.enviroTab).setBlockTextureName("enviromine:elevator_top_icon");
		elevatorBottom = new BlockElevatorBottom(Material.iron).setBlockName("elevator_bottom").setCreativeTab(EnviroMine.enviroTab).setBlockTextureName("enviromine:elevator_bottom_icon");
	}
	
	public static void registerBlocks()
	{
		GameRegistry.registerBlock(gasBlock, "gas");
		GameRegistry.registerBlock(fireGasBlock, "firegas");
		GameRegistry.registerBlock(elevatorTop, "elevator_top");
		GameRegistry.registerBlock(elevatorBottom, "elevator_bottom");
	}
	
	public static void registerGases()
	{
	}
	
	public static void registerEntities()
	{
		EntityRegistry.registerGlobalEntityID(EntityPhysicsBlock.class, "EnviroPhysicsBlock", EM_Settings.physBlockID);
		EntityRegistry.registerModEntity(EntityPhysicsBlock.class, "EnviroPhysicsEntity", EM_Settings.physBlockID, EnviroMine.instance, 64, 1, true);
		GameRegistry.registerTileEntity(TileEntityGas.class, "enviromine.tile.gas");
		
		GameRegistry.registerTileEntity(TileEntityElevatorTop.class, "enviromine.tile.elevator_top");
		GameRegistry.registerTileEntity(TileEntityElevatorBottom.class, "enviromine.tile.elevator_bottom");
	}
	
	public static void registerRecipes()
	{
		GameRegistry.addSmelting(badWaterBottle, new ItemStack(Items.potionitem, 1, 0), 0.0F);
		GameRegistry.addSmelting(saltWaterBottle, new ItemStack(Items.potionitem, 1, 0), 0.0F);
		GameRegistry.addSmelting(coldWaterBottle, new ItemStack(Items.potionitem, 1, 0), 0.0F);
		GameRegistry.addShapelessRecipe(new ItemStack(coldWaterBottle, 1, 0), new ItemStack(Items.potionitem, 1, 0), new ItemStack(Items.snowball, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(badWaterBottle, 1, 0), new ItemStack(Items.potionitem, 1, 0), new ItemStack(Blocks.dirt, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(saltWaterBottle, 1, 0), new ItemStack(Items.potionitem, 1, 0), new ItemStack(Blocks.sand, 1));
		
		GameRegistry.addRecipe(new ItemStack(Items.slime_ball, 1, 0), " r ", "rwr", " r ", 'w', new ItemStack(Items.water_bucket, 1, 0), 'r', new ItemStack(rottenFood, 1));
		GameRegistry.addRecipe(new ItemStack(Blocks.mycelium), "xyx", "yzy", "xyx", 'z', new ItemStack(Blocks.grass), 'x', new ItemStack(Blocks.brown_mushroom_block), 'y', new ItemStack(rottenFood, 1));
		GameRegistry.addRecipe(new ItemStack(Blocks.mycelium), "xyx", "yzy", "xyx", 'z', new ItemStack(Blocks.grass), 'y', new ItemStack(Blocks.brown_mushroom_block), 'x', new ItemStack(rottenFood, 1));
		GameRegistry.addRecipe(new ItemStack(Blocks.dirt, 1), "xxx", "xxx", "xxx", 'x', new ItemStack(rottenFood, 1));
		
		GameRegistry.addRecipe(new ItemStack(camelPack, 1, camelPack.getMaxDamage()), "xxx", "xyx", "xxx", 'x', new ItemStack(Items.leather), 'y', new ItemStack(Items.glass_bottle));
		GameRegistry.addRecipe(new ItemStack(airFilter, 1), "xyx", "xzx", "xyx", 'x', new ItemStack(Items.iron_ingot), 'y', new ItemStack(Blocks.wool), 'z', new ItemStack(Items.coal, 1, 1));
		GameRegistry.addRecipe(new ItemStack(gasMask, 1), "xxx", "xzx", "yxy", 'x', new ItemStack(Items.iron_ingot), 'y', new ItemStack(airFilter), 'z', new ItemStack(Blocks.glass_pane));
		GameRegistry.addRecipe(new ItemStack(hardHat, 1), "xyx", "xzx", 'x', new ItemStack(Blocks.wool, 1, 4), 'y', new ItemStack(Blocks.redstone_lamp), 'z', new ItemStack(Items.iron_helmet, 1, 0));
		
		GameRegistry.addRecipe(new ItemStack(elevatorTop), "xyx", "z z", "z z", 'x', new ItemStack(Blocks.iron_block), 'y', new ItemStack(Blocks.redstone_lamp), 'z', new ItemStack(Blocks.iron_bars));
		GameRegistry.addRecipe(new ItemStack(elevatorBottom), "z z", "xyx", "www", 'x', new ItemStack(Blocks.iron_block), 'y', new ItemStack(Blocks.furnace), 'z', new ItemStack(Blocks.iron_bars), 'w', new ItemStack(Items.diamond_pickaxe));
	}
	
	//TODO Should Probably be be Removed Sounds.Json now controls this
	//@ForgeSubscribe
	public void registerSounds(SoundLoadEvent event)
	{
		/*
		// You add them the same way as you add blocks.
		System.out.println("Loading Sounds");
		
		event.manager.addSound("enviromine:gasmask.ogg");
		
		event.manager.addSound("enviromine:thingdistant.ogg");
		event.manager.addSound("enviromine:thingkill.ogg");
		
		event.manager.addSound("enviromine:CaveIn.ogg");
		
		event.manager.addSound("enviromine:sizzle.ogg");
		event.manager.addSound("enviromine:chill.ogg");
		
		//Random Heavy(Panic) Breathing
		event.manager.addSound("enviromine:gag1.ogg");
		event.manager.addSound("enviromine:gag2.ogg");
		event.manager.addSound("enviromine:gag3.ogg");
		*/
	}
}
