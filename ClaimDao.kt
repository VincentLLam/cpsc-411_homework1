package Projects.cpsc411.homework1.claim

import csuf.cpsc411.Dao.Dao
import csuf.cpsc411.Dao.Database

class ClaimDao : Dao() {
    fun addClaim(claimObj : Claim) {
        val conn = Database.getInstance()?.getDbConnection()

        sqlStmt = "insert into claim (id, title, date, isSolved) values ('${claimObj.id}', '${claimObj.title}', '${claimObj.date}', '${claimObj.isSolved}')"

        conn?.exec(sqlStmt)
    }

    fun getAll() : List<Claim> {
        val conn = Database.getInstance()?.getDbConnection()
        sqlStmt = "select id, title, date, isSolved from claim"
        var claimList : MutableList<Claim> = mutableListOf()
        val st = conn?.prepare(sqlStmt)

        while (st?.step()!!) {
            val id = st.columnString(0)
            val title = st.columnString(1)
            val date = st.columnString(2)
            val isSolved = st.columnString(3)

            claimList.add(Claim(id, title, date, isSolved.toBoolean()))
        }

        return claimList
    }
}