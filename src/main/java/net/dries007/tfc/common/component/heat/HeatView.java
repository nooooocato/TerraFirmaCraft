/*
 * Licensed under the EUPL, Version 1.2.
 * You may obtain a copy of the Licence at:
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-eupl-12
 */

package net.dries007.tfc.common.component.heat;

import net.minecraft.world.item.ItemStack;

import net.dries007.tfc.common.component.ComponentView;
import net.dries007.tfc.common.component.TFCComponents;
import net.dries007.tfc.util.calendar.Calendars;

public final class HeatView extends ComponentView<HeatComponent> implements IHeat
{
    HeatView(ItemStack stack, HeatComponent value)
    {
        super(stack, value, TFCComponents.HEAT);
    }

    @Override
    public float getTemperature()
    {
        return HeatCapability.adjustTemp(component.lastTemperature(), getHeatCapacity(), Calendars.get().getTicks() - component.lastTick());
    }

    @Override
    public void setTemperature(float temperature)
    {
        apply(component.with(temperature, Calendars.get().getTicks()));
    }

    @Override
    public float getHeatCapacity()
    {
        return component.heatCapacity() != 0f ? component.heatCapacity() : component.parent().heatCapacity();
    }

    @Override
    public float getWorkingTemperature()
    {
        return component.parent().forgingTemperature();
    }

    @Override
    public float getWeldingTemperature()
    {
        return component.parent().weldingTemperature();
    }
}