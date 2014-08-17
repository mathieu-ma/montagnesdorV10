package fr.montagnesdor.restaurant.struts.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jopendocument.dom.ODPackage;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

import fr.montagnesdor.restaurant.model.hibernate.Cashing;
import fr.montagnesdor.restaurant.services.receipts.ReceiptsManagerFactory;

public final class DaylyReceiptsListUploadAction extends Action
{
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
		DiskFileUpload fu = new DiskFileUpload();
		// If file size exceeds, a FileUploadException will be thrown
		fu.setSizeMax(1000000);

		List fileItems = null;
		try {
			fileItems = fu.parseRequest(request);
		} catch (FileUploadException e1) {
		}
		if (fileItems != null) {
			Iterator itr = fileItems.iterator();
			while (itr.hasNext()) {
				FileItem fi = (FileItem) itr.next();

				// Check if not form field so as to only handle the file inputs
				// else condition handles the submit button input
				if (!fi.isFormField()) {
					
//					uploadByCsv(fi);
					uploadByOds(fi);
				}
			}
		}

		return (mapping.findForward("success"));
	}

    private void uploadByCsv(FileItem fi) {
    	BufferedReader br = null;
		try {
			InputStream is = fi.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			// Skip the first line
			String line = br.readLine();
			while((line=br.readLine()) != null) {
				if (line.equals("")) {
					// Get out when blank first line found
					break;
				}
				StringTokenizer sb = new StringTokenizer(line, ";");
				Cashing cashing = null;
				try {
					if(sb.hasMoreTokens()) {
						Long id = new Long(sb.nextToken());
						// Load bean from database
						cashing = ReceiptsManagerFactory.getManager().getCashing(id);
					}
					if(sb.hasMoreTokens()) {
						// Skip the table number
						sb.nextToken();
					}
					if(sb.hasMoreTokens()) {
						Float cash = new Float(sb.nextToken().replace(',', '.')).floatValue();
						cashing.setCash(cash);
					}
					if(sb.hasMoreTokens()) {
						Float ticket = new Float(sb.nextToken().replace(',', '.')).floatValue();
						cashing.setTicket(ticket);
					}
					if(sb.hasMoreTokens()) {
						Float cheque = new Float(sb.nextToken().replace(',', '.')).floatValue();
						cashing.setCheque(cheque);
					}
					if(sb.hasMoreTokens()) {
						Float card = new Float(sb.nextToken().replace(',', '.')).floatValue();
						cashing.setCard(card);
					}
					if(sb.hasMoreTokens()) {
						Float online = new Float(sb.nextToken().replace(',', '.')).floatValue();
						cashing.setOnline(online);
					}
					if(sb.hasMoreTokens()) {
						Float unpaid = new Float(sb.nextToken().replace(',', '.')).floatValue();
						cashing.setUnpaid(unpaid);
					}
				} catch(Exception e) {
					// Any exception occur then Continue the next line
				}
				if (cashing != null) {
					// Only update data
					ReceiptsManagerFactory.getManager().saveOrUpdateCashing(cashing);
				}
			}
		} catch (Exception e) {
		} finally {
			try {
				br.close();
			} catch (IOException e) {
			}
		}
    }

    private void uploadByOds(FileItem fi) {
    	InputStream is = null;
		try {
			is = fi.getInputStream();
			ODPackage odPackage = new ODPackage(is);
			odPackage.putFile("styles.xml", odPackage.getDocument("content.xml"));
			SpreadSheet spreadSheet = SpreadSheet.create(odPackage);
			Sheet sheet = spreadSheet.getSheet(0);
			int rowCount = sheet.getRowCount();
			for (int i = 0; i < rowCount; i++) {
				// Skip header
				if (i == 0) {
					continue;
				}
				Cashing cashing = null;
				try {
					Float idFloat = new Float(sheet.getValueAt(0, i).toString());
					// Load bean from database
					cashing = ReceiptsManagerFactory.getManager().getCashing(new Long(idFloat.longValue()));
	
					Float cash = new Float(sheet.getValueAt(2, i).toString()).floatValue();
					cashing.setCash(cash);
	
					Float ticket = new Float(sheet.getValueAt(3, i).toString()).floatValue();
					cashing.setTicket(ticket);
	
					Float cheque = new Float(sheet.getValueAt(4, i).toString()).floatValue();
					cashing.setCheque(cheque);
	
					Float card = new Float(sheet.getValueAt(5, i).toString()).floatValue();
					cashing.setCard(card);
	
					Float online = new Float(sheet.getValueAt(6, i).toString()).floatValue();
					cashing.setOnline(online);
	
					Float unpaid = new Float(sheet.getValueAt(7, i).toString()).floatValue();
					cashing.setUnpaid(unpaid);
				} catch(Exception e) {
					// Any exception occur then Continue the next line
					e.printStackTrace();
				}
				if (cashing != null) {
					// Only update data
					ReceiptsManagerFactory.getManager().saveOrUpdateCashing(cashing);
				}
			}
		} catch (Exception e) {
		} finally {
			try {
				is.close();
			} catch (IOException e) {
			}
		}
    }

}
