package com.pedrojm96.core;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Material;


/**
 * Contiene los metodos para usar los materiales que sean compatible en multiples versiones en el servidor de minecraft implementando la api de bukkt/spigot.
 * 
 * @author PedroJM96
 * @version 1.0 22-09-2018
 *
 */
public enum  CoreMaterial2 {
	
	/**
	 * + 1.8.8
	 */ 
	ACACIA_DOOR (196,"LEGACY_ACACIA_DOOR"),
	ACACIA_DOOR_ITEM (430,"ACACIA_DOOR","LEGACY_ACACIA_DOOR_ITEM"),
	ACACIA_FENCE (430,"LEGACY_ACACIA_FENCE");
	
	
	
	private int id;

	private String[] versionDependentNames;
	
	
	private Material materialCache = null;
	
	
	
	
	
	
	private CoreMaterial2(int id,String... arg) {
		this.id = id;
		this.versionDependentNames = arg;
	}
	
	public Material getMaterial(){
		if (materialCache != null) return materialCache;
		try {
            return materialCache = Material.valueOf(this.name());
        } catch (IllegalArgumentException ignore1) {
             // try next
			 for (String name : versionDependentNames) {
			     try {
			         return materialCache = Material.valueOf(name);
			     } catch (IllegalArgumentException ignore2) {
			         // try next
			     }
			 }
        }
        throw new IllegalArgumentException("Found no valid material name for " + this.name());
	}

	
	private int getId() {
		return this.id;
	}
	
	
	
	
	
	public static Material getMaterial(int id) {
		Material retorno = Material.AIR;
		for(CoreMaterial2 mate : CoreMaterial2.values()) {
			if(mate.getId() == id) {
				retorno =  mate.getMaterial();
				break;
			}
		}
		return retorno;
	}

	private static Map<Integer, String> idpre113 = new HashMap<Integer, String>();
	private static Map<Integer, String> idpos113 = new HashMap<Integer, String>();
	 
	static {
		//anten de la 1.13
		idpre113.put(0,"AIR");
		idpre113.put(1,"STONE");
		idpre113.put(2,"GRASS");
		idpre113.put(3,"DIRT");
		idpre113.put(4,"COBBLESTONE");
		idpre113.put(5,"WOOD");
		idpre113.put(6,"SAPLING");
		idpre113.put(7,"BEDROCK");
		idpre113.put(8,"WATER");
		idpre113.put(9,"STATIONARY_WATER");
		idpre113.put(10,"LAVA");
		idpre113.put(11,"STATIONARY_LAVA");
		idpre113.put(12,"SAND");
		idpre113.put(13,"GRAVEL");
		idpre113.put(14,"GOLD_ORE");
		idpre113.put(15,"IRON_ORE");
		idpre113.put(16,"COAL_ORE");
		idpre113.put(17,"LOG");
		idpre113.put(18,"LEAVES");
		idpre113.put(19,"SPONGE");
		idpre113.put(20,"GLASS");
		idpre113.put(21,"LAPIS_ORE");
		idpre113.put(22,"LAPIS_BLOCK");
		idpre113.put(23,"DISPENSER");
		idpre113.put(24,"SANDSTONE");
		idpre113.put(25,"NOTE_BLOCK");
		idpre113.put(26,"BED_BLOCK");
		idpre113.put(27,"POWERED_RAIL");
		idpre113.put(28,"DETECTOR_RAIL");
		idpre113.put(29,"PISTON_STICKY_BASE");
		idpre113.put(30,"WEB");
		idpre113.put(31,"LONG_GRASS");
		idpre113.put(32,"DEAD_BUSH");
		idpre113.put(33,"PISTON_BASE");
		idpre113.put(34,"PISTON_EXTENSION");
		idpre113.put(35,"WOOL");
		idpre113.put(36,"PISTON_MOVING_PIECE");
		idpre113.put(37,"YELLOW_FLOWER");
		idpre113.put(38,"RED_ROSE");
		idpre113.put(39,"BROWN_MUSHROOM");
		idpre113.put(40,"RED_MUSHROOM");
		idpre113.put(41,"GOLD_BLOCK");
		idpre113.put(42,"IRON_BLOCK");
		idpre113.put(43,"DOUBLE_STEP");
		idpre113.put(44,"STEP");
		idpre113.put(45,"BRICK");
		idpre113.put(46,"TNT");
		idpre113.put(47,"BOOKSHELF");
		idpre113.put(48,"MOSSY_COBBLESTONE");
		idpre113.put(49,"OBSIDIAN");
		idpre113.put(50,"TORCH");
		idpre113.put(51,"FIRE");
		idpre113.put(52,"MOB_SPAWNER");
		idpre113.put(53,"WOOD_STAIRS");
		idpre113.put(54,"CHEST");
		idpre113.put(55,"REDSTONE_WIRE");
		idpre113.put(56,"DIAMOND_ORE");
		idpre113.put(57,"DIAMOND_BLOCK");
		idpre113.put(58,"WORKBENCH");
		idpre113.put(59,"CROPS");
		idpre113.put(60,"SOIL");
		idpre113.put(61,"FURNACE");
		idpre113.put(62,"BURNING_FURNACE");
		idpre113.put(63,"SIGN_POST");
		idpre113.put(64,"WOODEN_DOOR");
		idpre113.put(65,"LADDER");
		idpre113.put(66,"RAILS");
		idpre113.put(67,"COBBLESTONE_STAIRS");
		idpre113.put(68,"WALL_SIGN");
		idpre113.put(69,"LEVER");
		idpre113.put(70,"STONE_PLATE");
		idpre113.put(71,"IRON_DOOR_BLOCK");
		idpre113.put(72,"WOOD_PLATE");
		idpre113.put(73,"REDSTONE_ORE");
		idpre113.put(74,"GLOWING_REDSTONE_ORE");
		idpre113.put(75,"REDSTONE_TORCH_OFF");
		idpre113.put(76,"REDSTONE_TORCH_ON");
		idpre113.put(77,"STONE_BUTTON");
		idpre113.put(78,"SNOW");
		idpre113.put(79,"ICE");
		idpre113.put(80,"SNOW_BLOCK");
		idpre113.put(81,"CACTUS");
		idpre113.put(82,"CLAY");
		idpre113.put(83,"SUGAR_CANE_BLOCK");
		idpre113.put(84,"JUKEBOX");
		idpre113.put(85,"FENCE");
		idpre113.put(86,"PUMPKIN");
		idpre113.put(87,"NETHERRACK");
		idpre113.put(88,"SOUL_SAND");
		idpre113.put(89,"GLOWSTONE");
		idpre113.put(90,"PORTAL");
		idpre113.put(91,"JACK_O_LANTERN");
		idpre113.put(92,"CAKE_BLOCK");
		idpre113.put(93,"DIODE_BLOCK_OFF");
		idpre113.put(94,"DIODE_BLOCK_ON");
		idpre113.put(95,"STAINED_GLASS");
		idpre113.put(96,"TRAP_DOOR");
		idpre113.put(97,"MONSTER_EGGS");
		idpre113.put(98,"SMOOTH_BRICK");
		idpre113.put(99,"HUGE_MUSHROOM_1");
		idpre113.put(100,"HUGE_MUSHROOM_2");
		idpre113.put(101,"IRON_FENCE");
		idpre113.put(102,"THIN_GLASS");
		idpre113.put(103,"MELON_BLOCK");
		idpre113.put(104,"PUMPKIN_STEM");
		idpre113.put(105,"MELON_STEM");
		idpre113.put(106,"VINE");
		idpre113.put(107,"FENCE_GATE");
		idpre113.put(108,"BRICK_STAIRS");
		idpre113.put(109,"SMOOTH_STAIRS");
		idpre113.put(110,"MYCEL");
		idpre113.put(111,"WATER_LILY");
		idpre113.put(112,"NETHER_BRICK");
		idpre113.put(113,"NETHER_FENCE");
		idpre113.put(114,"NETHER_BRICK_STAIRS");
		idpre113.put(115,"NETHER_WARTS");
		idpre113.put(116,"ENCHANTMENT_TABLE");
		idpre113.put(117,"BREWING_STAND");
		idpre113.put(118,"CAULDRON");
		idpre113.put(119,"ENDER_PORTAL");
		idpre113.put(120,"ENDER_PORTAL_FRAME");
		idpre113.put(121,"ENDER_STONE");
		idpre113.put(122,"DRAGON_EGG");
		idpre113.put(123,"REDSTONE_LAMP_OFF");
		idpre113.put(124,"REDSTONE_LAMP_ON");
		idpre113.put(125,"WOOD_DOUBLE_STEP");
		idpre113.put(126,"WOOD_STEP");
		idpre113.put(127,"COCOA");
		idpre113.put(128,"SANDSTONE_STAIRS");
		idpre113.put(129,"EMERALD_ORE");
		idpre113.put(130,"ENDER_CHEST");
		idpre113.put(131,"TRIPWIRE_HOOK");
		idpre113.put(132,"TRIPWIRE");
		idpre113.put(133,"EMERALD_BLOCK");
		idpre113.put(134,"SPRUCE_WOOD_STAIRS");
		idpre113.put(135,"BIRCH_WOOD_STAIRS");
		idpre113.put(136,"JUNGLE_WOOD_STAIRS");
		idpre113.put(137,"COMMAND");
		idpre113.put(138,"BEACON");
		idpre113.put(139,"COBBLE_WALL");
		idpre113.put(140,"FLOWER_POT");
		idpre113.put(141,"CARROT");
		idpre113.put(142,"POTATO");
		idpre113.put(143,"WOOD_BUTTON");
		idpre113.put(144,"SKULL");
		idpre113.put(145,"ANVIL");
		idpre113.put(146,"TRAPPED_CHEST");
		idpre113.put(147,"GOLD_PLATE");
		idpre113.put(148,"IRON_PLATE");
		idpre113.put(149,"REDSTONE_COMPARATOR_OFF");
		idpre113.put(150,"REDSTONE_COMPARATOR_ON");
		idpre113.put(151,"DAYLIGHT_DETECTOR");
		idpre113.put(152,"REDSTONE_BLOCK");
		idpre113.put(153,"QUARTZ_ORE");
		idpre113.put(154,"HOPPER");
		idpre113.put(155,"QUARTZ_BLOCK");
		idpre113.put(156,"QUARTZ_STAIRS");
		idpre113.put(157,"ACTIVATOR_RAIL");
		idpre113.put(158,"DROPPER");
		idpre113.put(159,"STAINED_CLAY");
		idpre113.put(160,"STAINED_GLASS_PANE");
		idpre113.put(161,"LEAVES_2");
		idpre113.put(162,"LOG_2");
		idpre113.put(163,"ACACIA_STAIRS");
		idpre113.put(164,"DARK_OAK_STAIRS");
		idpre113.put(165,"SLIME_BLOCK");
		idpre113.put(166,"BARRIER");
		idpre113.put(167,"IRON_TRAPDOOR");
		idpre113.put(168,"PRISMARINE");
		idpre113.put(169,"SEA_LANTERN");
		idpre113.put(170,"HAY_BLOCK");
		idpre113.put(171,"CARPET");
		idpre113.put(172,"HARD_CLAY");
		idpre113.put(173,"COAL_BLOCK");
		idpre113.put(174,"PACKED_ICE");
		idpre113.put(175,"DOUBLE_PLANT");
		idpre113.put(176,"STANDING_BANNER");
		idpre113.put(177,"WALL_BANNER");
		idpre113.put(178,"DAYLIGHT_DETECTOR_INVERTED");
		idpre113.put(179,"RED_SANDSTONE");
		idpre113.put(180,"RED_SANDSTONE_STAIRS");
		idpre113.put(181,"DOUBLE_STONE_SLAB2");
		idpre113.put(182,"STONE_SLAB2");
		idpre113.put(183,"SPRUCE_FENCE_GATE");
		idpre113.put(184,"BIRCH_FENCE_GATE");
		idpre113.put(185,"JUNGLE_FENCE_GATE");
		idpre113.put(186,"DARK_OAK_FENCE_GATE");
		idpre113.put(187,"ACACIA_FENCE_GATE");
		idpre113.put(188,"SPRUCE_FENCE");
		idpre113.put(189,"BIRCH_FENCE");
		idpre113.put(190,"JUNGLE_FENCE");
		idpre113.put(191,"DARK_OAK_FENCE");
		idpre113.put(192,"ACACIA_FENCE");
		idpre113.put(193,"SPRUCE_DOOR");
		idpre113.put(194,"BIRCH_DOOR");
		idpre113.put(195,"JUNGLE_DOOR");
		idpre113.put(196,"ACACIA_DOOR");
		idpre113.put(197,"DARK_OAK_DOOR");
		idpre113.put(198,"END_ROD");
		idpre113.put(199,"CHORUS_PLANT");
		idpre113.put(200,"CHORUS_FLOWER");
		idpre113.put(201,"PURPUR_BLOCK");
		idpre113.put(202,"PURPUR_PILLAR");
		idpre113.put(203,"PURPUR_STAIRS");
		idpre113.put(204,"PURPUR_DOUBLE_SLAB");
		idpre113.put(205,"PURPUR_SLAB");
		idpre113.put(206,"END_BRICKS");
		idpre113.put(207,"BEETROOT_BLOCK");
		idpre113.put(208,"GRASS_PATH");
		idpre113.put(209,"END_GATEWAY");
		idpre113.put(210,"COMMAND_REPEATING");
		idpre113.put(211,"COMMAND_CHAIN");
		idpre113.put(212,"FROSTED_ICE");
		idpre113.put(213,"MAGMA");
		idpre113.put(214,"NETHER_WART_BLOCK");
		idpre113.put(215,"RED_NETHER_BRICK");
		idpre113.put(216,"BONE_BLOCK");
		idpre113.put(217,"STRUCTURE_VOID");
		idpre113.put(218,"OBSERVER");
		idpre113.put(219,"WHITE_SHULKER_BOX");
		idpre113.put(220,"ORANGE_SHULKER_BOX");
		idpre113.put(221,"MAGENTA_SHULKER_BOX");
		idpre113.put(222,"LIGHT_BLUE_SHULKER_BOX");
		idpre113.put(223,"YELLOW_SHULKER_BOX");
		idpre113.put(224,"LIME_SHULKER_BOX");
		idpre113.put(225,"PINK_SHULKER_BOX");
		idpre113.put(226,"GRAY_SHULKER_BOX");
		idpre113.put(227,"SILVER_SHULKER_BOX");
		idpre113.put(228,"CYAN_SHULKER_BOX");
		idpre113.put(229,"PURPLE_SHULKER_BOX");
		idpre113.put(230,"BLUE_SHULKER_BOX");
		idpre113.put(231,"BROWN_SHULKER_BOX");
		idpre113.put(232,"GREEN_SHULKER_BOX");
		idpre113.put(233,"RED_SHULKER_BOX");
		idpre113.put(234,"BLACK_SHULKER_BOX");
		idpre113.put(235,"WHITE_GLAZED_TERRACOTTA");
		idpre113.put(236,"ORANGE_GLAZED_TERRACOTTA");
		idpre113.put(237,"MAGENTA_GLAZED_TERRACOTTA");
		idpre113.put(238,"LIGHT_BLUE_GLAZED_TERRACOTTA");
		idpre113.put(239,"YELLOW_GLAZED_TERRACOTTA");
		idpre113.put(240,"LIME_GLAZED_TERRACOTTA");
		idpre113.put(241,"PINK_GLAZED_TERRACOTTA");
		idpre113.put(242,"GRAY_GLAZED_TERRACOTTA");
		idpre113.put(243,"SILVER_GLAZED_TERRACOTTA");
		idpre113.put(244,"CYAN_GLAZED_TERRACOTTA");
		idpre113.put(245,"PURPLE_GLAZED_TERRACOTTA");
		idpre113.put(246,"BLUE_GLAZED_TERRACOTTA");
		idpre113.put(247,"BROWN_GLAZED_TERRACOTTA");
		idpre113.put(248,"GREEN_GLAZED_TERRACOTTA");
		idpre113.put(249,"RED_GLAZED_TERRACOTTA");
		idpre113.put(250,"BLACK_GLAZED_TERRACOTTA");
		idpre113.put(251,"CONCRETE");
		idpre113.put(252,"CONCRETE_POWDER");
		idpre113.put(255,"STRUCTURE_BLOCK");
		idpre113.put(256,"IRON_SPADE");
		idpre113.put(257,"IRON_PICKAXE");
		idpre113.put(258,"IRON_AXE");
		idpre113.put(259,"FLINT_AND_STEEL");
		idpre113.put(260,"APPLE");
		idpre113.put(261,"BOW");
		idpre113.put(262,"ARROW");
		idpre113.put(263,"COAL");
		idpre113.put(264,"DIAMOND");
		idpre113.put(265,"IRON_INGOT");
		idpre113.put(266,"GOLD_INGOT");
		idpre113.put(267,"IRON_SWORD");
		idpre113.put(268,"WOOD_SWORD");
		idpre113.put(269,"WOOD_SPADE");
		idpre113.put(270,"WOOD_PICKAXE");
		idpre113.put(271,"WOOD_AXE");
		idpre113.put(272,"STONE_SWORD");
		idpre113.put(273,"STONE_SPADE");
		idpre113.put(274,"STONE_PICKAXE");
		idpre113.put(275,"STONE_AXE");
		idpre113.put(276,"DIAMOND_SWORD");
		idpre113.put(277,"DIAMOND_SPADE");
		idpre113.put(278,"DIAMOND_PICKAXE");
		idpre113.put(279,"DIAMOND_AXE");
		idpre113.put(280,"STICK");
		idpre113.put(281,"BOWL");
		idpre113.put(282,"MUSHROOM_SOUP");
		idpre113.put(283,"GOLD_SWORD");
		idpre113.put(284,"GOLD_SPADE");
		idpre113.put(285,"GOLD_PICKAXE");
		idpre113.put(286,"GOLD_AXE");
		idpre113.put(287,"STRING");
		idpre113.put(288,"FEATHER");
		idpre113.put(289,"SULPHUR");
		idpre113.put(290,"WOOD_HOE");
		idpre113.put(291,"STONE_HOE");
		idpre113.put(292,"IRON_HOE");
		idpre113.put(293,"DIAMOND_HOE");
		idpre113.put(294,"GOLD_HOE");
		idpre113.put(295,"SEEDS");
		idpre113.put(296,"WHEAT");
		idpre113.put(297,"BREAD");
		idpre113.put(298,"LEATHER_HELMET");
		idpre113.put(299,"LEATHER_CHESTPLATE");
		idpre113.put(300,"LEATHER_LEGGINGS");
		idpre113.put(301,"LEATHER_BOOTS");
		idpre113.put(302,"CHAINMAIL_HELMET");
		idpre113.put(303,"CHAINMAIL_CHESTPLATE");
		idpre113.put(304,"CHAINMAIL_LEGGINGS");
		idpre113.put(305,"CHAINMAIL_BOOTS");
		idpre113.put(306,"IRON_HELMET");
		idpre113.put(307,"IRON_CHESTPLATE");
		idpre113.put(308,"IRON_LEGGINGS");
		idpre113.put(309,"IRON_BOOTS");
		idpre113.put(310,"DIAMOND_HELMET");
		idpre113.put(311,"DIAMOND_CHESTPLATE");
		idpre113.put(312,"DIAMOND_LEGGINGS");
		idpre113.put(313,"DIAMOND_BOOTS");
		idpre113.put(314,"GOLD_HELMET");
		idpre113.put(315,"GOLD_CHESTPLATE");
		idpre113.put(316,"GOLD_LEGGINGS");
		idpre113.put(317,"GOLD_BOOTS");
		idpre113.put(318,"FLINT");
		idpre113.put(319,"PORK");
		idpre113.put(320,"GRILLED_PORK");
		idpre113.put(321,"PAINTING");
		idpre113.put(322,"GOLDEN_APPLE");
		idpre113.put(323,"SIGN");
		idpre113.put(324,"WOOD_DOOR");
		idpre113.put(325,"BUCKET");
		idpre113.put(326,"WATER_BUCKET");
		idpre113.put(327,"LAVA_BUCKET");
		idpre113.put(328,"MINECART");
		idpre113.put(329,"SADDLE");
		idpre113.put(330,"IRON_DOOR");
		idpre113.put(331,"REDSTONE");
		idpre113.put(332,"SNOW_BALL");
		idpre113.put(333,"BOAT");
		idpre113.put(334,"LEATHER");
		idpre113.put(335,"MILK_BUCKET");
		idpre113.put(336,"CLAY_BRICK");
		idpre113.put(337,"CLAY_BALL");
		idpre113.put(338,"SUGAR_CANE");
		idpre113.put(339,"PAPER");
		idpre113.put(340,"BOOK");
		idpre113.put(341,"SLIME_BALL");
		idpre113.put(342,"STORAGE_MINECART");
		idpre113.put(343,"POWERED_MINECART");
		idpre113.put(344,"EGG");
		idpre113.put(345,"COMPASS");
		idpre113.put(346,"FISHING_ROD");
		idpre113.put(347,"WATCH");
		idpre113.put(348,"GLOWSTONE_DUST");
		idpre113.put(349,"RAW_FISH");
		idpre113.put(350,"COOKED_FISH");
		idpre113.put(351,"INK_SACK");
		idpre113.put(352,"BONE");
		idpre113.put(353,"SUGAR");
		idpre113.put(354,"CAKE");
		idpre113.put(355,"BED");
		idpre113.put(356,"DIODE");
		idpre113.put(357,"COOKIE");
		idpre113.put(358,"MAP");
		idpre113.put(359,"SHEARS");
		idpre113.put(360,"MELON");
		idpre113.put(361,"PUMPKIN_SEEDS");
		idpre113.put(362,"MELON_SEEDS");
		idpre113.put(363,"RAW_BEEF");
		idpre113.put(364,"COOKED_BEEF");
		idpre113.put(365,"RAW_CHICKEN");
		idpre113.put(366,"COOKED_CHICKEN");
		idpre113.put(367,"ROTTEN_FLESH");
		idpre113.put(368,"ENDER_PEARL");
		idpre113.put(369,"BLAZE_ROD");
		idpre113.put(370,"GHAST_TEAR");
		idpre113.put(371,"GOLD_NUGGET");
		idpre113.put(372,"NETHER_STALK");
		idpre113.put(373,"POTION");
		idpre113.put(374,"GLASS_BOTTLE");
		idpre113.put(375,"SPIDER_EYE");
		idpre113.put(376,"FERMENTED_SPIDER_EYE");
		idpre113.put(377,"BLAZE_POWDER");
		idpre113.put(378,"MAGMA_CREAM");
		idpre113.put(379,"BREWING_STAND_ITEM");
		idpre113.put(380,"CAULDRON_ITEM");
		idpre113.put(381,"EYE_OF_ENDER");
		idpre113.put(382,"SPECKLED_MELON");
		idpre113.put(383,"MONSTER_EGG");
		idpre113.put(384,"EXP_BOTTLE");
		idpre113.put(385,"FIREBALL");
		idpre113.put(386,"BOOK_AND_QUILL");
		idpre113.put(387,"WRITTEN_BOOK");
		idpre113.put(388,"EMERALD");
		idpre113.put(389,"ITEM_FRAME");
		idpre113.put(390,"FLOWER_POT_ITEM");
		idpre113.put(391,"CARROT_ITEM");
		idpre113.put(392,"POTATO_ITEM");
		idpre113.put(393,"BAKED_POTATO");
		idpre113.put(394,"POISONOUS_POTATO");
		idpre113.put(395,"EMPTY_MAP");
		idpre113.put(396,"GOLDEN_CARROT");
		idpre113.put(397,"SKULL_ITEM");
		idpre113.put(398,"CARROT_STICK");
		idpre113.put(399,"NETHER_STAR");
		idpre113.put(400,"PUMPKIN_PIE");
		idpre113.put(401,"FIREWORK");
		idpre113.put(402,"FIREWORK_CHARGE");
		idpre113.put(403,"ENCHANTED_BOOK");
		idpre113.put(404,"REDSTONE_COMPARATOR");
		idpre113.put(405,"NETHER_BRICK_ITEM");
		idpre113.put(406,"QUARTZ");
		idpre113.put(407,"EXPLOSIVE_MINECART");
		idpre113.put(408,"HOPPER_MINECART");
		idpre113.put(409,"PRISMARINE_SHARD");
		idpre113.put(410,"PRISMARINE_CRYSTALS");
		idpre113.put(411,"RABBIT");
		idpre113.put(412,"COOKED_RABBIT");
		idpre113.put(413,"RABBIT_STEW");
		idpre113.put(414,"RABBIT_FOOT");
		idpre113.put(415,"RABBIT_HIDE");
		idpre113.put(416,"ARMOR_STAND");
		idpre113.put(417,"IRON_BARDING");
		idpre113.put(418,"GOLD_BARDING");
		idpre113.put(419,"DIAMOND_BARDING");
		idpre113.put(420,"LEASH");
		idpre113.put(421,"NAME_TAG");
		idpre113.put(422,"COMMAND_MINECART");
		idpre113.put(423,"MUTTON");
		idpre113.put(424,"COOKED_MUTTON");
		idpre113.put(425,"BANNER");
		idpre113.put(426,"END_CRYSTAL");
		idpre113.put(427,"SPRUCE_DOOR_ITEM");
		idpre113.put(428,"BIRCH_DOOR_ITEM");
		idpre113.put(429,"JUNGLE_DOOR_ITEM");
		idpre113.put(430,"ACACIA_DOOR_ITEM");
		idpre113.put(431,"DARK_OAK_DOOR_ITEM");
		idpre113.put(432,"CHORUS_FRUIT");
		idpre113.put(433,"CHORUS_FRUIT_POPPED");
		idpre113.put(434,"BEETROOT");
		idpre113.put(435,"BEETROOT_SEEDS");
		idpre113.put(436,"BEETROOT_SOUP");
		idpre113.put(437,"DRAGONS_BREATH");
		idpre113.put(438,"SPLASH_POTION");
		idpre113.put(439,"SPECTRAL_ARROW");
		idpre113.put(440,"TIPPED_ARROW");
		idpre113.put(441,"LINGERING_POTION");
		idpre113.put(442,"SHIELD");
		idpre113.put(443,"ELYTRA");
		idpre113.put(444,"BOAT_SPRUCE");
		idpre113.put(445,"BOAT_BIRCH");
		idpre113.put(446,"BOAT_JUNGLE");
		idpre113.put(447,"BOAT_ACACIA");
		idpre113.put(448,"BOAT_DARK_OAK");
		idpre113.put(449,"TOTEM");
		idpre113.put(450,"SHULKER_SHELL");
		idpre113.put(452,"IRON_NUGGET");
		idpre113.put(453,"KNOWLEDGE_BOOK");
		idpre113.put(2256,"GOLD_RECORD");
		idpre113.put(2257,"GREEN_RECORD");
		idpre113.put(2258,"RECORD_3");
		idpre113.put(2259,"RECORD_4");
		idpre113.put(2260,"RECORD_5");
		idpre113.put(2261,"RECORD_6");
		idpre113.put(2262,"RECORD_7");
		idpre113.put(2263,"RECORD_8");
		idpre113.put(2264,"RECORD_9");
		idpre113.put(2265,"RECORD_10");
		idpre113.put(2266,"RECORD_11");
		idpre113.put(2267,"RECORD_12");
		
		//despues de la 1.13
		idpos113.put(0,"LEGACY_AIR");
		idpos113.put(1,"LEGACY_STONE");
		idpos113.put(2,"LEGACY_GRASS");
		idpos113.put(3,"LEGACY_DIRT");
		idpos113.put(4,"LEGACY_COBBLESTONE");
		idpos113.put(5,"LEGACY_WOOD");
		idpos113.put(6,"LEGACY_SAPLING");
		idpos113.put(7,"LEGACY_BEDROCK");
		idpos113.put(8,"LEGACY_WATER");
		idpos113.put(9,"LEGACY_STATIONARY_WATER");
		idpos113.put(10,"LEGACY_LAVA");
		idpos113.put(11,"LEGACY_STATIONARY_LAVA");
		idpos113.put(12,"LEGACY_SAND");
		idpos113.put(13,"LEGACY_GRAVEL");
		idpos113.put(14,"LEGACY_GOLD_ORE");
		idpos113.put(15,"LEGACY_IRON_ORE");
		idpos113.put(16,"LEGACY_COAL_ORE");
		idpos113.put(17,"LEGACY_LOG");
		idpos113.put(18,"LEGACY_LEAVES");
		idpos113.put(19,"LEGACY_SPONGE");
		idpos113.put(20,"LEGACY_GLASS");
		idpos113.put(21,"LEGACY_LAPIS_ORE");
		idpos113.put(22,"LEGACY_LAPIS_BLOCK");
		idpos113.put(23,"LEGACY_DISPENSER");
		idpos113.put(24,"LEGACY_SANDSTONE");
		idpos113.put(25,"LEGACY_NOTE_BLOCK");
		idpos113.put(26,"LEGACY_BED_BLOCK");
		idpos113.put(27,"LEGACY_POWERED_RAIL");
		idpos113.put(28,"LEGACY_DETECTOR_RAIL");
		idpos113.put(29,"LEGACY_PISTON_STICKY_BASE");
		idpos113.put(30,"LEGACY_WEB");
		idpos113.put(31,"LEGACY_LONG_GRASS");
		idpos113.put(32,"LEGACY_DEAD_BUSH");
		idpos113.put(33,"LEGACY_PISTON_BASE");
		idpos113.put(34,"LEGACY_PISTON_EXTENSION");
		idpos113.put(35,"LEGACY_WOOL");
		idpos113.put(36,"LEGACY_PISTON_MOVING_PIECE");
		idpos113.put(37,"LEGACY_YELLOW_FLOWER");
		idpos113.put(38,"LEGACY_RED_ROSE");
		idpos113.put(39,"LEGACY_BROWN_MUSHROOM");
		idpos113.put(40,"LEGACY_RED_MUSHROOM");
		idpos113.put(41,"LEGACY_GOLD_BLOCK");
		idpos113.put(42,"LEGACY_IRON_BLOCK");
		idpos113.put(43,"LEGACY_DOUBLE_STEP");
		idpos113.put(44,"LEGACY_STEP");
		idpos113.put(45,"LEGACY_BRICK");
		idpos113.put(46,"LEGACY_TNT");
		idpos113.put(47,"LEGACY_BOOKSHELF");
		idpos113.put(48,"LEGACY_MOSSY_COBBLESTONE");
		idpos113.put(49,"LEGACY_OBSIDIAN");
		idpos113.put(50,"LEGACY_TORCH");
		idpos113.put(51,"LEGACY_FIRE");
		idpos113.put(52,"LEGACY_MOB_SPAWNER");
		idpos113.put(53,"LEGACY_WOOD_STAIRS");
		idpos113.put(54,"LEGACY_CHEST");
		idpos113.put(55,"LEGACY_REDSTONE_WIRE");
		idpos113.put(56,"LEGACY_DIAMOND_ORE");
		idpos113.put(57,"LEGACY_DIAMOND_BLOCK");
		idpos113.put(58,"LEGACY_WORKBENCH");
		idpos113.put(59,"LEGACY_CROPS");
		idpos113.put(60,"LEGACY_SOIL");
		idpos113.put(61,"LEGACY_FURNACE");
		idpos113.put(62,"LEGACY_BURNING_FURNACE");
		idpos113.put(63,"LEGACY_SIGN_POST");
		idpos113.put(64,"LEGACY_WOODEN_DOOR");
		idpos113.put(65,"LEGACY_LADDER");
		idpos113.put(66,"LEGACY_RAILS");
		idpos113.put(67,"LEGACY_COBBLESTONE_STAIRS");
		idpos113.put(68,"LEGACY_WALL_SIGN");
		idpos113.put(69,"LEGACY_LEVER");
		idpos113.put(70,"LEGACY_STONE_PLATE");
		idpos113.put(71,"LEGACY_IRON_DOOR_BLOCK");
		idpos113.put(72,"LEGACY_WOOD_PLATE");
		idpos113.put(73,"LEGACY_REDSTONE_ORE");
		idpos113.put(74,"LEGACY_GLOWING_REDSTONE_ORE");
		idpos113.put(75,"LEGACY_REDSTONE_TORCH_OFF");
		idpos113.put(76,"LEGACY_REDSTONE_TORCH_ON");
		idpos113.put(77,"LEGACY_STONE_BUTTON");
		idpos113.put(78,"LEGACY_SNOW");
		idpos113.put(79,"LEGACY_ICE");
		idpos113.put(80,"LEGACY_SNOW_BLOCK");
		idpos113.put(81,"LEGACY_CACTUS");
		idpos113.put(82,"LEGACY_CLAY");
		idpos113.put(83,"LEGACY_SUGAR_CANE_BLOCK");
		idpos113.put(84,"LEGACY_JUKEBOX");
		idpos113.put(85,"LEGACY_FENCE");
		idpos113.put(86,"LEGACY_PUMPKIN");
		idpos113.put(87,"LEGACY_NETHERRACK");
		idpos113.put(88,"LEGACY_SOUL_SAND");
		idpos113.put(89,"LEGACY_GLOWSTONE");
		idpos113.put(90,"LEGACY_PORTAL");
		idpos113.put(91,"LEGACY_JACK_O_LANTERN");
		idpos113.put(92,"LEGACY_CAKE_BLOCK");
		idpos113.put(93,"LEGACY_DIODE_BLOCK_OFF");
		idpos113.put(94,"LEGACY_DIODE_BLOCK_ON");
		idpos113.put(95,"LEGACY_STAINED_GLASS");
		idpos113.put(96,"LEGACY_TRAP_DOOR");
		idpos113.put(97,"LEGACY_MONSTER_EGGS");
		idpos113.put(98,"LEGACY_SMOOTH_BRICK");
		idpos113.put(99,"LEGACY_HUGE_MUSHROOM_1");
		idpos113.put(100,"LEGACY_HUGE_MUSHROOM_2");
		idpos113.put(101,"LEGACY_IRON_FENCE");
		idpos113.put(102,"LEGACY_THIN_GLASS");
		idpos113.put(103,"LEGACY_MELON_BLOCK");
		idpos113.put(104,"LEGACY_PUMPKIN_STEM");
		idpos113.put(105,"LEGACY_MELON_STEM");
		idpos113.put(106,"LEGACY_VINE");
		idpos113.put(107,"LEGACY_FENCE_GATE");
		idpos113.put(108,"LEGACY_BRICK_STAIRS");
		idpos113.put(109,"LEGACY_SMOOTH_STAIRS");
		idpos113.put(110,"LEGACY_MYCEL");
		idpos113.put(111,"LEGACY_WATER_LILY");
		idpos113.put(112,"LEGACY_NETHER_BRICK");
		idpos113.put(113,"LEGACY_NETHER_FENCE");
		idpos113.put(114,"LEGACY_NETHER_BRICK_STAIRS");
		idpos113.put(115,"LEGACY_NETHER_WARTS");
		idpos113.put(116,"LEGACY_ENCHANTMENT_TABLE");
		idpos113.put(117,"LEGACY_BREWING_STAND");
		idpos113.put(118,"LEGACY_CAULDRON");
		idpos113.put(119,"LEGACY_ENDER_PORTAL");
		idpos113.put(120,"LEGACY_ENDER_PORTAL_FRAME");
		idpos113.put(121,"LEGACY_ENDER_STONE");
		idpos113.put(122,"LEGACY_DRAGON_EGG");
		idpos113.put(123,"LEGACY_REDSTONE_LAMP_OFF");
		idpos113.put(124,"LEGACY_REDSTONE_LAMP_ON");
		idpos113.put(125,"LEGACY_WOOD_DOUBLE_STEP");
		idpos113.put(126,"LEGACY_WOOD_STEP");
		idpos113.put(127,"LEGACY_COCOA");
		idpos113.put(128,"LEGACY_SANDSTONE_STAIRS");
		idpos113.put(129,"LEGACY_EMERALD_ORE");
		idpos113.put(130,"LEGACY_ENDER_CHEST");
		idpos113.put(131,"LEGACY_TRIPWIRE_HOOK");
		idpos113.put(132,"LEGACY_TRIPWIRE");
		idpos113.put(133,"LEGACY_EMERALD_BLOCK");
		idpos113.put(134,"LEGACY_SPRUCE_WOOD_STAIRS");
		idpos113.put(135,"LEGACY_BIRCH_WOOD_STAIRS");
		idpos113.put(136,"LEGACY_JUNGLE_WOOD_STAIRS");
		idpos113.put(137,"LEGACY_COMMAND");
		idpos113.put(138,"LEGACY_BEACON");
		idpos113.put(139,"LEGACY_COBBLE_WALL");
		idpos113.put(140,"LEGACY_FLOWER_POT");
		idpos113.put(141,"LEGACY_CARROT");
		idpos113.put(142,"LEGACY_POTATO");
		idpos113.put(143,"LEGACY_WOOD_BUTTON");
		idpos113.put(144,"LEGACY_SKULL");
		idpos113.put(145,"LEGACY_ANVIL");
		idpos113.put(146,"LEGACY_TRAPPED_CHEST");
		idpos113.put(147,"LEGACY_GOLD_PLATE");
		idpos113.put(148,"LEGACY_IRON_PLATE");
		idpos113.put(149,"LEGACY_REDSTONE_COMPARATOR_OFF");
		idpos113.put(150,"LEGACY_REDSTONE_COMPARATOR_ON");
		idpos113.put(151,"LEGACY_DAYLIGHT_DETECTOR");
		idpos113.put(152,"LEGACY_REDSTONE_BLOCK");
		idpos113.put(153,"LEGACY_QUARTZ_ORE");
		idpos113.put(154,"LEGACY_HOPPER");
		idpos113.put(155,"LEGACY_QUARTZ_BLOCK");
		idpos113.put(156,"LEGACY_QUARTZ_STAIRS");
		idpos113.put(157,"LEGACY_ACTIVATOR_RAIL");
		idpos113.put(158,"LEGACY_DROPPER");
		idpos113.put(159,"LEGACY_STAINED_CLAY");
		idpos113.put(160,"LEGACY_STAINED_GLASS_PANE");
		idpos113.put(161,"LEGACY_LEAVES_2");
		idpos113.put(162,"LEGACY_LOG_2");
		idpos113.put(163,"LEGACY_ACACIA_STAIRS");
		idpos113.put(164,"LEGACY_DARK_OAK_STAIRS");
		idpos113.put(165,"LEGACY_SLIME_BLOCK");
		idpos113.put(166,"LEGACY_BARRIER");
		idpos113.put(167,"LEGACY_IRON_TRAPDOOR");
		idpos113.put(168,"LEGACY_PRISMARINE");
		idpos113.put(169,"LEGACY_SEA_LANTERN");
		idpos113.put(170,"LEGACY_HAY_BLOCK");
		idpos113.put(171,"LEGACY_CARPET");
		idpos113.put(172,"LEGACY_HARD_CLAY");
		idpos113.put(173,"LEGACY_COAL_BLOCK");
		idpos113.put(174,"LEGACY_PACKED_ICE");
		idpos113.put(175,"LEGACY_DOUBLE_PLANT");
		idpos113.put(176,"LEGACY_STANDING_BANNER");
		idpos113.put(177,"LEGACY_WALL_BANNER");
		idpos113.put(178,"LEGACY_DAYLIGHT_DETECTOR_INVERTED");
		idpos113.put(179,"LEGACY_RED_SANDSTONE");
		idpos113.put(180,"LEGACY_RED_SANDSTONE_STAIRS");
		idpos113.put(181,"LEGACY_DOUBLE_STONE_SLAB2");
		idpos113.put(182,"LEGACY_STONE_SLAB2");
		idpos113.put(183,"LEGACY_SPRUCE_FENCE_GATE");
		idpos113.put(184,"LEGACY_BIRCH_FENCE_GATE");
		idpos113.put(185,"LEGACY_JUNGLE_FENCE_GATE");
		idpos113.put(186,"LEGACY_DARK_OAK_FENCE_GATE");
		idpos113.put(187,"LEGACY_ACACIA_FENCE_GATE");
		idpos113.put(188,"LEGACY_SPRUCE_FENCE");
		idpos113.put(189,"LEGACY_BIRCH_FENCE");
		idpos113.put(190,"LEGACY_JUNGLE_FENCE");
		idpos113.put(191,"LEGACY_DARK_OAK_FENCE");
		idpos113.put(192,"LEGACY_ACACIA_FENCE");
		idpos113.put(193,"LEGACY_SPRUCE_DOOR");
		idpos113.put(194,"LEGACY_BIRCH_DOOR");
		idpos113.put(195,"LEGACY_JUNGLE_DOOR");
		idpos113.put(196,"LEGACY_ACACIA_DOOR");
		idpos113.put(197,"LEGACY_DARK_OAK_DOOR");
		idpos113.put(198,"LEGACY_END_ROD");
		idpos113.put(199,"LEGACY_CHORUS_PLANT");
		idpos113.put(200,"LEGACY_CHORUS_FLOWER");
		idpos113.put(201,"LEGACY_PURPUR_BLOCK");
		idpos113.put(202,"LEGACY_PURPUR_PILLAR");
		idpos113.put(203,"LEGACY_PURPUR_STAIRS");
		idpos113.put(204,"LEGACY_PURPUR_DOUBLE_SLAB");
		idpos113.put(205,"LEGACY_PURPUR_SLAB");
		idpos113.put(206,"LEGACY_END_BRICKS");
		idpos113.put(207,"LEGACY_BEETROOT_BLOCK");
		idpos113.put(208,"LEGACY_GRASS_PATH");
		idpos113.put(209,"LEGACY_END_GATEWAY");
		idpos113.put(210,"LEGACY_COMMAND_REPEATING");
		idpos113.put(211,"LEGACY_COMMAND_CHAIN");
		idpos113.put(212,"LEGACY_FROSTED_ICE");
		idpos113.put(213,"LEGACY_MAGMA");
		idpos113.put(214,"LEGACY_NETHER_WART_BLOCK");
		idpos113.put(215,"LEGACY_RED_NETHER_BRICK");
		idpos113.put(216,"LEGACY_BONE_BLOCK");
		idpos113.put(217,"LEGACY_STRUCTURE_VOID");
		idpos113.put(218,"LEGACY_OBSERVER");
		idpos113.put(219,"LEGACY_WHITE_SHULKER_BOX");
		idpos113.put(220,"LEGACY_ORANGE_SHULKER_BOX");
		idpos113.put(221,"LEGACY_MAGENTA_SHULKER_BOX");
		idpos113.put(222,"LEGACY_LIGHT_BLUE_SHULKER_BOX");
		idpos113.put(223,"LEGACY_YELLOW_SHULKER_BOX");
		idpos113.put(224,"LEGACY_LIME_SHULKER_BOX");
		idpos113.put(225,"LEGACY_PINK_SHULKER_BOX");
		idpos113.put(226,"LEGACY_GRAY_SHULKER_BOX");
		idpos113.put(227,"LEGACY_SILVER_SHULKER_BOX");
		idpos113.put(228,"LEGACY_CYAN_SHULKER_BOX");
		idpos113.put(229,"LEGACY_PURPLE_SHULKER_BOX");
		idpos113.put(230,"LEGACY_BLUE_SHULKER_BOX");
		idpos113.put(231,"LEGACY_BROWN_SHULKER_BOX");
		idpos113.put(232,"LEGACY_GREEN_SHULKER_BOX");
		idpos113.put(233,"LEGACY_RED_SHULKER_BOX");
		idpos113.put(234,"LEGACY_BLACK_SHULKER_BOX");
		idpos113.put(235,"LEGACY_WHITE_GLAZED_TERRACOTTA");
		idpos113.put(236,"LEGACY_ORANGE_GLAZED_TERRACOTTA");
		idpos113.put(237,"LEGACY_MAGENTA_GLAZED_TERRACOTTA");
		idpos113.put(238,"LEGACY_LIGHT_BLUE_GLAZED_TERRACOTTA");
		idpos113.put(239,"LEGACY_YELLOW_GLAZED_TERRACOTTA");
		idpos113.put(240,"LEGACY_LIME_GLAZED_TERRACOTTA");
		idpos113.put(241,"LEGACY_PINK_GLAZED_TERRACOTTA");
		idpos113.put(242,"LEGACY_GRAY_GLAZED_TERRACOTTA");
		idpos113.put(243,"LEGACY_SILVER_GLAZED_TERRACOTTA");
		idpos113.put(244,"LEGACY_CYAN_GLAZED_TERRACOTTA");
		idpos113.put(245,"LEGACY_PURPLE_GLAZED_TERRACOTTA");
		idpos113.put(246,"LEGACY_BLUE_GLAZED_TERRACOTTA");
		idpos113.put(247,"LEGACY_BROWN_GLAZED_TERRACOTTA");
		idpos113.put(248,"LEGACY_GREEN_GLAZED_TERRACOTTA");
		idpos113.put(249,"LEGACY_RED_GLAZED_TERRACOTTA");
		idpos113.put(250,"LEGACY_BLACK_GLAZED_TERRACOTTA");
		idpos113.put(251,"LEGACY_CONCRETE");
		idpos113.put(252,"LEGACY_CONCRETE_POWDER");
		idpos113.put(255,"LEGACY_STRUCTURE_BLOCK");
		idpos113.put(256,"LEGACY_IRON_SPADE");
		idpos113.put(257,"LEGACY_IRON_PICKAXE");
		idpos113.put(258,"LEGACY_IRON_AXE");
		idpos113.put(259,"LEGACY_FLINT_AND_STEEL");
		idpos113.put(260,"LEGACY_APPLE");
		idpos113.put(261,"LEGACY_BOW");
		idpos113.put(262,"LEGACY_ARROW");
		idpos113.put(263,"LEGACY_COAL");
		idpos113.put(264,"LEGACY_DIAMOND");
		idpos113.put(265,"LEGACY_IRON_INGOT");
		idpos113.put(266,"LEGACY_GOLD_INGOT");
		idpos113.put(267,"LEGACY_IRON_SWORD");
		idpos113.put(268,"LEGACY_WOOD_SWORD");
		idpos113.put(269,"LEGACY_WOOD_SPADE");
		idpos113.put(270,"LEGACY_WOOD_PICKAXE");
		idpos113.put(271,"LEGACY_WOOD_AXE");
		idpos113.put(272,"LEGACY_STONE_SWORD");
		idpos113.put(273,"LEGACY_STONE_SPADE");
		idpos113.put(274,"LEGACY_STONE_PICKAXE");
		idpos113.put(275,"LEGACY_STONE_AXE");
		idpos113.put(276,"LEGACY_DIAMOND_SWORD");
		idpos113.put(277,"LEGACY_DIAMOND_SPADE");
		idpos113.put(278,"LEGACY_DIAMOND_PICKAXE");
		idpos113.put(279,"LEGACY_DIAMOND_AXE");
		idpos113.put(280,"LEGACY_STICK");
		idpos113.put(281,"LEGACY_BOWL");
		idpos113.put(282,"LEGACY_MUSHROOM_SOUP");
		idpos113.put(283,"LEGACY_GOLD_SWORD");
		idpos113.put(284,"LEGACY_GOLD_SPADE");
		idpos113.put(285,"LEGACY_GOLD_PICKAXE");
		idpos113.put(286,"LEGACY_GOLD_AXE");
		idpos113.put(287,"LEGACY_STRING");
		idpos113.put(288,"LEGACY_FEATHER");
		idpos113.put(289,"LEGACY_SULPHUR");
		idpos113.put(290,"LEGACY_WOOD_HOE");
		idpos113.put(291,"LEGACY_STONE_HOE");
		idpos113.put(292,"LEGACY_IRON_HOE");
		idpos113.put(293,"LEGACY_DIAMOND_HOE");
		idpos113.put(294,"LEGACY_GOLD_HOE");
		idpos113.put(295,"LEGACY_SEEDS");
		idpos113.put(296,"LEGACY_WHEAT");
		idpos113.put(297,"LEGACY_BREAD");
		idpos113.put(298,"LEGACY_LEATHER_HELMET");
		idpos113.put(299,"LEGACY_LEATHER_CHESTPLATE");
		idpos113.put(300,"LEGACY_LEATHER_LEGGINGS");
		idpos113.put(301,"LEGACY_LEATHER_BOOTS");
		idpos113.put(302,"LEGACY_CHAINMAIL_HELMET");
		idpos113.put(303,"LEGACY_CHAINMAIL_CHESTPLATE");
		idpos113.put(304,"LEGACY_CHAINMAIL_LEGGINGS");
		idpos113.put(305,"LEGACY_CHAINMAIL_BOOTS");
		idpos113.put(306,"LEGACY_IRON_HELMET");
		idpos113.put(307,"LEGACY_IRON_CHESTPLATE");
		idpos113.put(308,"LEGACY_IRON_LEGGINGS");
		idpos113.put(309,"LEGACY_IRON_BOOTS");
		idpos113.put(310,"LEGACY_DIAMOND_HELMET");
		idpos113.put(311,"LEGACY_DIAMOND_CHESTPLATE");
		idpos113.put(312,"LEGACY_DIAMOND_LEGGINGS");
		idpos113.put(313,"LEGACY_DIAMOND_BOOTS");
		idpos113.put(314,"LEGACY_GOLD_HELMET");
		idpos113.put(315,"LEGACY_GOLD_CHESTPLATE");
		idpos113.put(316,"LEGACY_GOLD_LEGGINGS");
		idpos113.put(317,"LEGACY_GOLD_BOOTS");
		idpos113.put(318,"LEGACY_FLINT");
		idpos113.put(319,"LEGACY_PORK");
		idpos113.put(320,"LEGACY_GRILLED_PORK");
		idpos113.put(321,"LEGACY_PAINTING");
		idpos113.put(322,"LEGACY_GOLDEN_APPLE");
		idpos113.put(323,"LEGACY_SIGN");
		idpos113.put(324,"LEGACY_WOOD_DOOR");
		idpos113.put(325,"LEGACY_BUCKET");
		idpos113.put(326,"LEGACY_WATER_BUCKET");
		idpos113.put(327,"LEGACY_LAVA_BUCKET");
		idpos113.put(328,"LEGACY_MINECART");
		idpos113.put(329,"LEGACY_SADDLE");
		idpos113.put(330,"LEGACY_IRON_DOOR");
		idpos113.put(331,"LEGACY_REDSTONE");
		idpos113.put(332,"LEGACY_SNOW_BALL");
		idpos113.put(333,"LEGACY_BOAT");
		idpos113.put(334,"LEGACY_LEATHER");
		idpos113.put(335,"LEGACY_MILK_BUCKET");
		idpos113.put(336,"LEGACY_CLAY_BRICK");
		idpos113.put(337,"LEGACY_CLAY_BALL");
		idpos113.put(338,"LEGACY_SUGAR_CANE");
		idpos113.put(339,"LEGACY_PAPER");
		idpos113.put(340,"LEGACY_BOOK");
		idpos113.put(341,"LEGACY_SLIME_BALL");
		idpos113.put(342,"LEGACY_STORAGE_MINECART");
		idpos113.put(343,"LEGACY_POWERED_MINECART");
		idpos113.put(344,"LEGACY_EGG");
		idpos113.put(345,"LEGACY_COMPASS");
		idpos113.put(346,"LEGACY_FISHING_ROD");
		idpos113.put(347,"LEGACY_WATCH");
		idpos113.put(348,"LEGACY_GLOWSTONE_DUST");
		idpos113.put(349,"LEGACY_RAW_FISH");
		idpos113.put(350,"LEGACY_COOKED_FISH");
		idpos113.put(351,"LEGACY_INK_SACK");
		idpos113.put(352,"LEGACY_BONE");
		idpos113.put(353,"LEGACY_SUGAR");
		idpos113.put(354,"LEGACY_CAKE");
		idpos113.put(355,"LEGACY_BED");
		idpos113.put(356,"LEGACY_DIODE");
		idpos113.put(357,"LEGACY_COOKIE");
		idpos113.put(358,"LEGACY_MAP");
		idpos113.put(359,"LEGACY_SHEARS");
		idpos113.put(360,"LEGACY_MELON");
		idpos113.put(361,"LEGACY_PUMPKIN_SEEDS");
		idpos113.put(362,"LEGACY_MELON_SEEDS");
		idpos113.put(363,"LEGACY_RAW_BEEF");
		idpos113.put(364,"LEGACY_COOKED_BEEF");
		idpos113.put(365,"LEGACY_RAW_CHICKEN");
		idpos113.put(366,"LEGACY_COOKED_CHICKEN");
		idpos113.put(367,"LEGACY_ROTTEN_FLESH");
		idpos113.put(368,"LEGACY_ENDER_PEARL");
		idpos113.put(369,"LEGACY_BLAZE_ROD");
		idpos113.put(370,"LEGACY_GHAST_TEAR");
		idpos113.put(371,"LEGACY_GOLD_NUGGET");
		idpos113.put(372,"LEGACY_NETHER_STALK");
		idpos113.put(373,"LEGACY_POTION");
		idpos113.put(374,"LEGACY_GLASS_BOTTLE");
		idpos113.put(375,"LEGACY_SPIDER_EYE");
		idpos113.put(376,"LEGACY_FERMENTED_SPIDER_EYE");
		idpos113.put(377,"LEGACY_BLAZE_POWDER");
		idpos113.put(378,"LEGACY_MAGMA_CREAM");
		idpos113.put(379,"LEGACY_BREWING_STAND_ITEM");
		idpos113.put(380,"LEGACY_CAULDRON_ITEM");
		idpos113.put(381,"LEGACY_EYE_OF_ENDER");
		idpos113.put(382,"LEGACY_SPECKLED_MELON");
		idpos113.put(383,"LEGACY_MONSTER_EGG");
		idpos113.put(384,"LEGACY_EXP_BOTTLE");
		idpos113.put(385,"LEGACY_FIREBALL");
		idpos113.put(386,"LEGACY_BOOK_AND_QUILL");
		idpos113.put(387,"LEGACY_WRITTEN_BOOK");
		idpos113.put(388,"LEGACY_EMERALD");
		idpos113.put(389,"LEGACY_ITEM_FRAME");
		idpos113.put(390,"LEGACY_FLOWER_POT_ITEM");
		idpos113.put(391,"LEGACY_CARROT_ITEM");
		idpos113.put(392,"LEGACY_POTATO_ITEM");
		idpos113.put(393,"LEGACY_BAKED_POTATO");
		idpos113.put(394,"LEGACY_POISONOUS_POTATO");
		idpos113.put(395,"LEGACY_EMPTY_MAP");
		idpos113.put(396,"LEGACY_GOLDEN_CARROT");
		idpos113.put(397,"LEGACY_SKULL_ITEM");
		idpos113.put(398,"LEGACY_CARROT_STICK");
		idpos113.put(399,"LEGACY_NETHER_STAR");
		idpos113.put(400,"LEGACY_PUMPKIN_PIE");
		idpos113.put(401,"LEGACY_FIREWORK");
		idpos113.put(402,"LEGACY_FIREWORK_CHARGE");
		idpos113.put(403,"LEGACY_ENCHANTED_BOOK");
		idpos113.put(404,"LEGACY_REDSTONE_COMPARATOR");
		idpos113.put(405,"LEGACY_NETHER_BRICK_ITEM");
		idpos113.put(406,"LEGACY_QUARTZ");
		idpos113.put(407,"LEGACY_EXPLOSIVE_MINECART");
		idpos113.put(408,"LEGACY_HOPPER_MINECART");
		idpos113.put(409,"LEGACY_PRISMARINE_SHARD");
		idpos113.put(410,"LEGACY_PRISMARINE_CRYSTALS");
		idpos113.put(411,"LEGACY_RABBIT");
		idpos113.put(412,"LEGACY_COOKED_RABBIT");
		idpos113.put(413,"LEGACY_RABBIT_STEW");
		idpos113.put(414,"LEGACY_RABBIT_FOOT");
		idpos113.put(415,"LEGACY_RABBIT_HIDE");
		idpos113.put(416,"LEGACY_ARMOR_STAND");
		idpos113.put(417,"LEGACY_IRON_BARDING");
		idpos113.put(418,"LEGACY_GOLD_BARDING");
		idpos113.put(419,"LEGACY_DIAMOND_BARDING");
		idpos113.put(420,"LEGACY_LEASH");
		idpos113.put(421,"LEGACY_NAME_TAG");
		idpos113.put(422,"LEGACY_COMMAND_MINECART");
		idpos113.put(423,"LEGACY_MUTTON");
		idpos113.put(424,"LEGACY_COOKED_MUTTON");
		idpos113.put(425,"LEGACY_BANNER");
		idpos113.put(426,"LEGACY_END_CRYSTAL");
		idpos113.put(427,"LEGACY_SPRUCE_DOOR_ITEM");
		idpos113.put(428,"LEGACY_BIRCH_DOOR_ITEM");
		idpos113.put(429,"LEGACY_JUNGLE_DOOR_ITEM");
		idpos113.put(430,"LEGACY_ACACIA_DOOR_ITEM");
		idpos113.put(431,"LEGACY_DARK_OAK_DOOR_ITEM");
		idpos113.put(432,"LEGACY_CHORUS_FRUIT");
		idpos113.put(433,"LEGACY_CHORUS_FRUIT_POPPED");
		idpos113.put(434,"LEGACY_BEETROOT");
		idpos113.put(435,"LEGACY_BEETROOT_SEEDS");
		idpos113.put(436,"LEGACY_BEETROOT_SOUP");
		idpos113.put(437,"LEGACY_DRAGONS_BREATH");
		idpos113.put(438,"LEGACY_SPLASH_POTION");
		idpos113.put(439,"LEGACY_SPECTRAL_ARROW");
		idpos113.put(440,"LEGACY_TIPPED_ARROW");
		idpos113.put(441,"LEGACY_LINGERING_POTION");
		idpos113.put(442,"LEGACY_SHIELD");
		idpos113.put(443,"LEGACY_ELYTRA");
		idpos113.put(444,"LEGACY_BOAT_SPRUCE");
		idpos113.put(445,"LEGACY_BOAT_BIRCH");
		idpos113.put(446,"LEGACY_BOAT_JUNGLE");
		idpos113.put(447,"LEGACY_BOAT_ACACIA");
		idpos113.put(448,"LEGACY_BOAT_DARK_OAK");
		idpos113.put(449,"LEGACY_TOTEM");
		idpos113.put(450,"LEGACY_SHULKER_SHELL");
		idpos113.put(452,"LEGACY_IRON_NUGGET");
		idpos113.put(453,"LEGACY_KNOWLEDGE_BOOK");
		idpos113.put(2256,"LEGACY_GOLD_RECORD");
		idpos113.put(2257,"LEGACY_GREEN_RECORD");
		idpos113.put(2258,"LEGACY_RECORD_3");
		idpos113.put(2259,"LEGACY_RECORD_4");
		idpos113.put(2260,"LEGACY_RECORD_5");
		idpos113.put(2261,"LEGACY_RECORD_6");
		idpos113.put(2262,"LEGACY_RECORD_7");
		idpos113.put(2263,"LEGACY_RECORD_8");
		idpos113.put(2264,"LEGACY_RECORD_9");
		idpos113.put(2265,"LEGACY_RECORD_10");
		idpos113.put(2266,"LEGACY_RECORD_11");
		idpos113.put(2267,"LEGACY_RECORD_12");
	}
	
	
	
	
}
