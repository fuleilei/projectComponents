package com.mine.project.carTest.sql

import android.content.Context
import com.mine.project.carTest.bean.ContactsInfo
import java.util.*

class ContactsDatabase(context: Context?) {
    private val dbHelper: DatabaseHelper

    /**
     * 增
     *
     * @param data
     */
    fun insert(data: ContactsInfo) {
        var sql = "insert into " + DatabaseHelper.CONTACTS_TABLE_NAME
        sql += "(sendPhone, name, phone) values(?,?,?)"
        val sqlite = dbHelper.writableDatabase
        sqlite.execSQL(sql, arrayOf(data.sendPhone + "", data.name + "", data.phone + ""))
        sqlite.close()
    }

    /**
     * 删
     *
     * @param where
     */
    fun delete(where: String) {
        val sqlite = dbHelper.writableDatabase
        val sql = "delete from " + DatabaseHelper.CONTACTS_TABLE_NAME + where
        sqlite.execSQL(sql)
        sqlite.close()
    }

    /**
     * 改
     *
     * @param data
     */
    fun update(data: ContactsInfo) {
        val sqlite = dbHelper.writableDatabase
        val sql =
            "update " + DatabaseHelper.CONTACTS_TABLE_NAME + " set sendPhone=?,name=?,phone=? where cid=?"
        sqlite.execSQL(
            sql,
            arrayOf(data.sendPhone + "", data.name + "", data.phone + "", data.cid.toString() + "")
        )
        sqlite.close()
    }

    /**
     * 查一条数据
     *
     * @param where
     * @return
     */
    fun queryContactsInfo(where: String): ContactsInfo? {
        var contactsInfo: ContactsInfo? = null
        val sqlite = dbHelper.readableDatabase
        val sql = ("select * from "
                + DatabaseHelper.CONTACTS_TABLE_NAME + where)
        val cursor = sqlite.rawQuery(sql, null)
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            contactsInfo = ContactsInfo()
            contactsInfo.cid = cursor.getInt(0)
            contactsInfo.sendPhone = cursor.getString(1)
            contactsInfo.name = cursor.getString(2)
            contactsInfo.phone = cursor.getString(3)
            cursor.moveToNext()
        }
        if (!cursor.isClosed) {
            cursor.close()
        }
        sqlite.close()
        return contactsInfo
    }

    /**
     * 查
     *
     * @param where
     * @return
     */
    fun query(where: String): List<ContactsInfo?> {
        val sqlite = dbHelper.readableDatabase
        var data: ArrayList<ContactsInfo?>? = null
        data = ArrayList()
        val sql = ("select * from "
                + DatabaseHelper.CONTACTS_TABLE_NAME + where)
        val cursor = sqlite.rawQuery(sql, null)
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            val contactsInfo = ContactsInfo()
            contactsInfo.sendPhone = cursor.getString(1)
            contactsInfo.name = cursor.getString(2)
            contactsInfo.phone = cursor.getString(3)
            data.add(contactsInfo)
            cursor.moveToNext()
        }
        if (!cursor.isClosed) {
            cursor.close()
        }
        sqlite.close()
        return data
    }

    /**
     * 查
     *
     * @param where
     * @return
     */
    fun queryCount(where: String): Int {
        val sqlite = dbHelper.readableDatabase
        val sql = ("select count(*) from "
                + DatabaseHelper.CONTACTS_TABLE_NAME + where)
        val cursor = sqlite.rawQuery(sql, null)
        cursor.moveToFirst()
        val count = cursor.getInt(0)
        sqlite.close()
        return count
    }

    /**
     * 重置
     *
     * @param datas
     */
    fun reset(datas: List<ContactsInfo>?) {
        if (datas != null) {
            val sqlite = dbHelper.writableDatabase
            // 删除全部
            sqlite.execSQL("delete from " + DatabaseHelper.CONTACTS_TABLE_NAME)
            // 重新添加
            for (data in datas) {
                insert(data)
            }
            sqlite.close()
        }
    }

    fun destroy() {
        dbHelper.close()
    }

    init {
        dbHelper = DatabaseHelper(context)
    }
}