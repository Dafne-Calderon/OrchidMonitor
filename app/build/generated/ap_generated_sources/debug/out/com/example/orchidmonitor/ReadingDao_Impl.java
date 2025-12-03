package com.example.orchidmonitor;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ReadingDao_Impl implements ReadingDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ReadingEntity> __insertionAdapterOfReadingEntity;

  public ReadingDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfReadingEntity = new EntityInsertionAdapter<ReadingEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `readings` (`id`,`fecha`,`humedad`,`temperatura`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          final ReadingEntity entity) {
        statement.bindLong(1, entity.id);
        if (entity.fecha == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.fecha);
        }
        statement.bindLong(3, entity.humedad);
        statement.bindDouble(4, entity.temperatura);
      }
    };
  }

  @Override
  public void insert(final ReadingEntity reading) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfReadingEntity.insert(reading);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<ReadingEntity> getAllReadings() {
    final String _sql = "SELECT * FROM readings ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfFecha = CursorUtil.getColumnIndexOrThrow(_cursor, "fecha");
      final int _cursorIndexOfHumedad = CursorUtil.getColumnIndexOrThrow(_cursor, "humedad");
      final int _cursorIndexOfTemperatura = CursorUtil.getColumnIndexOrThrow(_cursor, "temperatura");
      final List<ReadingEntity> _result = new ArrayList<ReadingEntity>(_cursor.getCount());
      while (_cursor.moveToNext()) {
        final ReadingEntity _item;
        final String _tmpFecha;
        if (_cursor.isNull(_cursorIndexOfFecha)) {
          _tmpFecha = null;
        } else {
          _tmpFecha = _cursor.getString(_cursorIndexOfFecha);
        }
        final int _tmpHumedad;
        _tmpHumedad = _cursor.getInt(_cursorIndexOfHumedad);
        final double _tmpTemperatura;
        _tmpTemperatura = _cursor.getDouble(_cursorIndexOfTemperatura);
        _item = new ReadingEntity(_tmpFecha,_tmpHumedad,_tmpTemperatura);
        _item.id = _cursor.getInt(_cursorIndexOfId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
