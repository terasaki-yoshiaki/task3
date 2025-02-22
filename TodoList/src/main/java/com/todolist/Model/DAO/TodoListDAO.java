package com.todolist.Model.DAO;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.todolist.Model.DTO.TodoListDTO;
import com.todolist.Model.DataBase.DataBase;



/**
 * DBにアクセスするクラス
 * @author SI
 *
 */
public class TodoListDAO {

	private Connection con = null;

	/**
	 * DBに接続する処理	
	 */
	public void connect() {
		try {
			// DBに接続
			con = DataBase.getConnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * todolistテーブルからデータを取得する処理
	 * @return 全件取得した結果
	 */
	public ArrayList<TodoListDTO> select() {
		// ステートメントはSQLを実行するオブジェクト
		Statement stmt = null;
		// リザルトセットは結果を格納するオブジェクト
		ResultSet rs = null;

		String sql = "SELECT * FROM kadai3.todolist";
		ArrayList<TodoListDTO> list = new ArrayList<TodoListDTO>();

		try {
			// DB接続のメソッドを呼び出す
			connect();

			// ステートメントを作成
			stmt = con.createStatement();

			// SQLを実行し結果をリザルトセットへ格納
			rs = stmt.executeQuery(sql);

			// 結果を1行ずつループt
			while (rs.next()) {
				TodoListDTO dto = new TodoListDTO();
				dto.setID(rs.getInt("id"));
				dto.setTodo(rs.getString("todo"));
				dto.setPlace(rs.getString("place"));
				
				
				String datetimeStr = rs.getString("date_time");
				//Date date = java.sql.Date.valueOf(datetimeStr);

		         
		        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		        Date fDt = sdFormat.parse(datetimeStr);
		        String str = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(fDt);
		         String formattedDatetime = String.valueOf(str);
				//SimpleDateFormat fDt = new SimpleDateFormat("yyyy/MM/dd HH:mm");
				//String formattedDatetime = date.format(datetimeStr);
				
				dto.setDatetime(formattedDatetime);
				
				dto.setMemo(rs.getString("memo"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}
	//void=戻り値無し
	public void insert(TodoListDTO dto) {
		// ステートメントはSQLを実行するオブジェクト
				Statement stmt = null;

				//dtoから各項目の値を取り出す get
				String to = dto.getTodo();
				String pl = dto.getPlace();
				String da = dto.getDatetime();
				String me = dto.getMemo();
				
				String sql = "INSERT\n"
						+ "INTO kadai3.todolist(todo,place,date_time,memo)\n"
						+ "VALUES ('"+to+"','"+pl+"','"+da+"','"+me+"')";
				
				
				try {
					// DB接続のメソッドを呼び出す
					connect();

					// ステートメントを作成
					stmt = con.createStatement();

					// SQLを実行し結果をリザルトセットへ格納
					stmt.executeUpdate(sql);
				
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						stmt.close();
						con.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				//voidの場合はreturnは無くてもいい

		return;
	}

	public void update(TodoListDTO dto) {
		// TODO 自動生成されたメソッド・スタブ
		Statement stmt = null;

		//dtoから各項目の値を取り出す get
		int ID = dto.getID();
		String to = dto.getTodo();
		String pl = dto.getPlace();
		String da = dto.getDatetime();
		String me = dto.getMemo();
		
		String sql = "UPDATE kadai3.todolist\n"
				+ "SET todo='"+to+"',place='"+pl+"',date_time='"+da+"',memo='"+me+"'\n"
				+ "WHERE id='"+ID+"'";
		
		
		try {
			// DB接続のメソッドを呼び出す
			connect();

			// ステートメントを作成
			stmt = con.createStatement();

			// SQLを実行し結果をリザルトセットへ格納
			stmt.executeUpdate(sql);
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


public void delete(TodoListDTO dto) {
	// TODO 自動生成されたメソッド・スタブ
	Statement stmt = null;

	//dtoから各項目の値を取り出す get
	int ID = dto.getID();
	//String to = dto.getTodo();
	//String pl = dto.getPlace();
	//String da = dto.getDatetime();
	//String me = dto.getMemo();
	
	String sql = "DELETE FROM kadai3.todolist\n"
			+ "WHERE id='"+ID+"'";
	
	
	try {
		// DB接続のメソッドを呼び出す
		connect();

		// ステートメントを作成
		stmt = con.createStatement();

		// SQLを実行し結果をリザルトセットへ格納
		stmt.executeUpdate(sql);
	
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


}

