package ar.edu.utn.frsf.dmg.teamflash.lab02.service.Impl;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;

import ar.edu.utn.frsf.dmg.teamflash.lab02.R;
import ar.edu.utn.frsf.dmg.teamflash.lab02.service.HorarioService;

/**
 * Created by AdminUser on 02/09/2016.
 */
public class HorarioServiceImpl implements HorarioService {
    private Context context;

    public HorarioServiceImpl(Context context) {
        this.context= context;
    }

    @Override
    public ArrayList<String> listaHorarios() {
        return new ArrayList<String>(Arrays.asList(context.getResources().getStringArray(R.array.horarios)));
    }
}
