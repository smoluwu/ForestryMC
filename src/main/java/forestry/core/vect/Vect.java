/*******************************************************************************
 * Copyright (c) 2011-2014 SirSengir.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v3
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Various Contributors including, but not limited to:
 * SirSengir (original work), CovertJaguar, Player, Binnie, MysteriousAges
 ******************************************************************************/
package forestry.core.vect;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

/**
 * Represents an unchangeable position or dimensions.
 */
public class Vect implements IVect {
	public final int x;
	public final int y;
	public final int z;

	public Vect(int[] dim) {
		if (dim.length != 3) {
			throw new RuntimeException("Cannot instantiate a vector with less or more than 3 points.");
		}

		this.x = dim[0];
		this.y = dim[1];
		this.z = dim[2];
	}

	public Vect(IVect vect) {
		this(vect.getX(), vect.getY(), vect.getZ());
	}

	public Vect(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vect(BlockPos pos) {
		this.x = pos.getX();
		this.y = pos.getY();
		this.z = pos.getZ();
	}

	public static Vect getRandomPositionInArea(Random random, IVect area) {
		int x = random.nextInt(area.getX());
		int y = random.nextInt(area.getY());
		int z = random.nextInt(area.getZ());
		return new Vect(x, y, z);
	}

	public static Vect add(IVect... vects) {
		int x = 0;
		int y = 0;
		int z = 0;
		for (IVect vect : vects) {
			x += vect.getX();
			y += vect.getY();
			z += vect.getZ();
		}
		return new Vect(x, y, z);
	}

	@Override
	public Vect add(IVect other) {
		return new Vect(x + other.getX(), y + other.getY(), z + other.getZ());
	}

	@Override
	public Vect add(int x, int y, int z) {
		return new Vect(this.x + x, this.y + y, this.z + z);
	}

	@Override
	public Vect add(EnumFacing direction) {
		return add(direction.getFrontOffsetX(), direction.getFrontOffsetY(), direction.getFrontOffsetZ());
	}

	public Vect multiply(float factor) {
		return new Vect(Math.round(x * factor), Math.round(y * factor), Math.round(z * factor));
	}

	@Override
	public String toString() {
		return String.format("%sx%sx%s", x, y, z);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		result = prime * result + z;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Vect)) {
			return false;
		}
		Vect other = (Vect) obj;
		return (x == other.x) && (y == other.y) && (z == other.z);
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int getZ() {
		return z;
	}

	@Override
	public BlockPos toBlockPos() {
		return new BlockPos(x, y, z);
	}
}
