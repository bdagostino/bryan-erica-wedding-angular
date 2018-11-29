import {NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {TemplateRef} from '@angular/core';

export abstract class BaseModal {

  protected constructor(private modalService: NgbModal) {
  }

  openModalBase(modalTemplate: TemplateRef<NgbModal>) {
    return this.modalService.open(modalTemplate, {ariaLabelledBy: 'modal-basic-title'});
  }

  closeModalBase(ngModalRef: NgbModalRef) {
    ngModalRef.close();
  }

  abstract openModal();
  abstract saveModal();
  abstract closeModal();

}
