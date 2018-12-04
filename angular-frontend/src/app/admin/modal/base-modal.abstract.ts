import {NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {TemplateRef} from '@angular/core';

export abstract class BaseModal {

  protected constructor(private _modalService: NgbModal) {
  }

  openModalBase(modalTemplate: TemplateRef<NgbModal>) {
    return this._modalService.open(modalTemplate);
  }

  closeModalBase(ngModalRef: NgbModalRef) {
    ngModalRef.close();
  }

  abstract openModal();
  abstract saveModal();
  abstract closeModal();

}
