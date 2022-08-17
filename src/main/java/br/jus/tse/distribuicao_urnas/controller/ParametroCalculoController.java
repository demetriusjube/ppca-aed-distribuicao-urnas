package br.jus.tse.distribuicao_urnas.controller;

import br.jus.tse.distribuicao_urnas.model.ParametroCalculoDTO;
import br.jus.tse.distribuicao_urnas.model.TipoParametroEnum;
import br.jus.tse.distribuicao_urnas.service.ParametroCalculoService;
import br.jus.tse.distribuicao_urnas.util.WebUtils;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/parametroCalculos")
public class ParametroCalculoController {

    private final ParametroCalculoService parametroCalculoService;

    public ParametroCalculoController(final ParametroCalculoService parametroCalculoService) {
        this.parametroCalculoService = parametroCalculoService;
    }

    @ModelAttribute
    public void prepareContext(final Model model) {
        model.addAttribute("tipoParametroValues", TipoParametroEnum.values());
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("parametroCalculos", parametroCalculoService.findAll());
        return "parametroCalculo/list";
    }

    @GetMapping("/add")
    public String add(
            @ModelAttribute("parametroCalculo") final ParametroCalculoDTO parametroCalculoDTO) {
        return "parametroCalculo/add";
    }

    @PostMapping("/add")
    public String add(
            @ModelAttribute("parametroCalculo") @Valid final ParametroCalculoDTO parametroCalculoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "parametroCalculo/add";
        }
        parametroCalculoService.create(parametroCalculoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("parametroCalculo.create.success"));
        return "redirect:/parametroCalculos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("parametroCalculo", parametroCalculoService.get(id));
        return "parametroCalculo/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("parametroCalculo") @Valid final ParametroCalculoDTO parametroCalculoDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "parametroCalculo/edit";
        }
        parametroCalculoService.update(id, parametroCalculoDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("parametroCalculo.update.success"));
        return "redirect:/parametroCalculos";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        parametroCalculoService.delete(id);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("parametroCalculo.delete.success"));
        return "redirect:/parametroCalculos";
    }

}
